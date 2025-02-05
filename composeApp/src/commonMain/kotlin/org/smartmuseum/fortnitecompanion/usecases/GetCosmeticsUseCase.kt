package org.smartmuseum.fortnitecompanion.usecases

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.smartmuseum.fortnitecompanion.data.cosmetics.Cosmetic
import org.smartmuseum.fortnitecompanion.data.cosmetics.CosmeticsResponse
import org.smartmuseum.fortnitecompanion.networking.FortniteApiInterface
import org.smartmuseum.fortnitecompanion.networking.NetworkResult
import org.smartmuseum.fortnitecompanion.networking.ResponseConverter
import org.smartmuseum.fortnitecompanion.networking.callApi

class GetCosmeticsUseCase(val repo: FortniteApiInterface, val language: String): KoinComponent {
    private val responseConverter: ResponseConverter by inject()
    suspend fun getCosmetics(): List<Cosmetic> {
        val cosmetics: NetworkResult<CosmeticsResponse> = withContext(Dispatchers.IO) {
            repo.callApi<CosmeticsResponse>(call = {repo.getCosmetics()}, responseConverter = responseConverter)
        }
        return if (cosmetics is NetworkResult.Success) {
            cosmetics.data.data.br
        } else listOf()
    }
}