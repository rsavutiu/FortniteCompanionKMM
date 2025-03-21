package org.smartmuseum.fortnitecompanion.usecases

import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.smartmuseum.fortnitecompanion.data.shop.ShopResponse
import org.smartmuseum.fortnitecompanion.networking.FortniteApiInterface
import org.smartmuseum.fortnitecompanion.networking.NetworkResult
import org.smartmuseum.fortnitecompanion.networking.ResponseConverter
import org.smartmuseum.fortnitecompanion.networking.callApi
import kotlin.coroutines.CoroutineContext

class GetShopDataUseCase(
    fortniteApi: FortniteApiInterface,
    language: String?,
    val coroutineContext: CoroutineContext,
) : KoinComponent, SingleUseCase<ShopResponse>(fortniteApi, language) {
    private val responseConverter: ResponseConverter by inject()

    override suspend fun loadCall(): NetworkResult<ShopResponse> =
        withContext(context = coroutineContext) {
            fortniteApi.callApi<ShopResponse>(
                call = { fortniteApi.getShop(language = language) },
                responseConverter = responseConverter
            )
        }
}