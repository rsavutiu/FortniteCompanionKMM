package org.smartmuseum.fortnitecompanion.usecases

import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.smartmuseum.fortnitecompanion.data.map.MapResponse
import org.smartmuseum.fortnitecompanion.networking.FortniteApiInterface
import org.smartmuseum.fortnitecompanion.networking.NetworkResult
import org.smartmuseum.fortnitecompanion.networking.ResponseConverter
import org.smartmuseum.fortnitecompanion.networking.callApi
import kotlin.coroutines.CoroutineContext

class GetMapUseCase(
    fortniteApi: FortniteApiInterface,
    language: String?,
    val coroutineContext: CoroutineContext,
) :
    KoinComponent, SingleUseCase<MapResponse>(fortniteApi, language) {
    private val responseConverter: ResponseConverter by inject()
    override suspend fun loadCall(): NetworkResult<MapResponse> =
        withContext(context = coroutineContext) {
            fortniteApi.callApi<MapResponse>(
                call = { fortniteApi.getBattleRoyaleMap(language = language) },
                responseConverter = responseConverter
            )
        }
}