package org.smartmuseum.fortnitecompanion.usecases

import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.smartmuseum.fortnitecompanion.data.stats.PlayerStatsResponse
import org.smartmuseum.fortnitecompanion.networking.FortniteApiInterface
import org.smartmuseum.fortnitecompanion.networking.NetworkResult
import org.smartmuseum.fortnitecompanion.networking.ResponseConverter
import org.smartmuseum.fortnitecompanion.networking.callApi

sealed class FindStatsResult {
    data class Success(val stats: PlayerStatsResponse) : FindStatsResult()
    data object Loading : FindStatsResult()
    data class AccountPrivate(val msg: String) : FindStatsResult()
    data class AccountNotfound(val msg: String) : FindStatsResult()
    data class UnknownError(val msg: String) : FindStatsResult()
}

class FindStatsUseCase(val fortniteApi: FortniteApiInterface, val language: String?) :
    KoinComponent {
    private var _statsResult = MutableStateFlow<FindStatsResult?>(null)
    var statsResult: StateFlow<FindStatsResult?> = _statsResult
    private val responseConverter: ResponseConverter by inject()
    suspend fun loadStat(name: String) {
        _statsResult.value = FindStatsResult.Loading
        val result = fortniteApi.callApi<PlayerStatsResponse>(
            call = { fortniteApi.getBattleRoyaleStats(name = name) },
            responseConverter = responseConverter
        )
        return when (result) {
            is NetworkResult.Success -> {
                _statsResult.value = FindStatsResult.Success(result.data)
            }

            is NetworkResult.Error -> {
                val exception = result.exception
                val status = result.status
                when (status) {
                    HttpStatusCode.Forbidden -> _statsResult.value =
                        FindStatsResult.AccountPrivate(exception.message ?: "")

                    HttpStatusCode.NotFound -> _statsResult.value =
                        FindStatsResult.AccountNotfound(exception.message ?: "")

                    else -> _statsResult.value =
                        FindStatsResult.UnknownError(exception.message ?: "")
                }
            }

            NetworkResult.Loading, NetworkResult.Ready -> _statsResult.value =
                FindStatsResult.Loading
        }
    }
}