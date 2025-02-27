package org.smartmuseum.fortnitecompanion.usecases

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.smartmuseum.fortnitecompanion.data.shop.ShopResponse
import org.smartmuseum.fortnitecompanion.networking.FortniteApiInterface
import org.smartmuseum.fortnitecompanion.networking.NetworkResult
import org.smartmuseum.fortnitecompanion.networking.ResponseConverter
import org.smartmuseum.fortnitecompanion.networking.callApi

sealed class GetShopResult {
    data object Ready : GetShopResult()
    data class Success(val stats: ShopResponse) : GetShopResult()
    data object Loading : GetShopResult()
    data class Error(val msg: String) : GetShopResult()
}

class GetShopDataUseCase(val fortniteApi: FortniteApiInterface, val language: String?) :
    KoinComponent {
    private var _shopResult = MutableStateFlow<GetShopResult>(GetShopResult.Ready)
    var shopResult: StateFlow<GetShopResult> = _shopResult
    private val responseConverter: ResponseConverter by inject()
    suspend fun loadShop() {
        val result = fortniteApi.callApi<ShopResponse>(
            call = { fortniteApi.getShop(language = language) },
            responseConverter = responseConverter
        )
        when (result) {
            is NetworkResult.Success -> {
                _shopResult.emit(GetShopResult.Success(stats = result.data))
            }

            is NetworkResult.Error -> {
                val exception = result.exception
                val status = result.status
                _shopResult.emit(
                    GetShopResult.Error("Error message: ${exception.message}. Error Status: $status")
                )
            }

            else -> {}
        }
    }
}