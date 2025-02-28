package org.smartmuseum.fortnitecompanion.usecases

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.component.KoinComponent
import org.smartmuseum.fortnitecompanion.networking.FortniteApiInterface
import org.smartmuseum.fortnitecompanion.networking.NetworkResult

class GenericSingleLoadUseCase<T>(
    val fortniteApi: FortniteApiInterface,
    val language: String?,
    val loadCall: (String?) -> NetworkResult<T>,
) :
    KoinComponent {
    private var _result: MutableStateFlow<NetworkResult<T>> = MutableStateFlow(NetworkResult.Ready)
    var result: StateFlow<NetworkResult<T>> = _result

    suspend fun loadShop() {
        val result: NetworkResult<T> = loadCall(language)
        when (result) {
            is NetworkResult.Success -> {
                _result.emit(NetworkResult.Success(result.data))
            }

            is NetworkResult.Error -> {
                _result.emit(
                    NetworkResult.Error(
                        exception = result.exception,
                        status = result.status
                    )
                )
            }

            else -> {}
        }
    }
}