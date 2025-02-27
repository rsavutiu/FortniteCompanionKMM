package org.smartmuseum.fortnitecompanion.usecases

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.smartmuseum.fortnitecompanion.data.cosmetics.BannerResponse
import org.smartmuseum.fortnitecompanion.data.cosmetics.BattleRoyaleCosmeticsResponse
import org.smartmuseum.fortnitecompanion.data.cosmetics.BeansCosmeticsResponse
import org.smartmuseum.fortnitecompanion.data.cosmetics.CarsCosmeticsResponse
import org.smartmuseum.fortnitecompanion.data.cosmetics.CosmeticEnum
import org.smartmuseum.fortnitecompanion.data.cosmetics.CosmeticsResponse
import org.smartmuseum.fortnitecompanion.data.cosmetics.ICosmetic
import org.smartmuseum.fortnitecompanion.data.cosmetics.InstrumentsCosmeticsResponse
import org.smartmuseum.fortnitecompanion.data.cosmetics.LegoCosmeticsResponse
import org.smartmuseum.fortnitecompanion.data.cosmetics.LegoKitsCosmeticsResponse
import org.smartmuseum.fortnitecompanion.data.cosmetics.NewCosmeticsResponse
import org.smartmuseum.fortnitecompanion.data.cosmetics.TracksCosmeticsResponse
import org.smartmuseum.fortnitecompanion.networking.FortniteApiInterface
import org.smartmuseum.fortnitecompanion.networking.NetworkResult
import org.smartmuseum.fortnitecompanion.networking.ResponseConverter
import org.smartmuseum.fortnitecompanion.networking.callApi

class GetCosmeticsUseCase(val fortniteApi: FortniteApiInterface, val language: String?) :
    KoinComponent {
    private var _cosmeticNetworkStatusMap: HashMap<CosmeticEnum, MutableStateFlow<NetworkResult<List<ICosmetic>>>> =
        hashMapOf()
    var cosmeticNetworkStatusMap: HashMap<CosmeticEnum, StateFlow<NetworkResult<List<ICosmetic>>>> =
        hashMapOf()

    init {
        for (enum in CosmeticEnum.entries) {
            _cosmeticNetworkStatusMap[enum] = MutableStateFlow(NetworkResult.Ready)
            cosmeticNetworkStatusMap[enum] = _cosmeticNetworkStatusMap[enum]!!
        }
    }
    private val responseConverter: ResponseConverter by inject()

    private val _bannerNetworkStatus: MutableStateFlow<NetworkResult<BannerResponse>> =
        MutableStateFlow(NetworkResult.Ready)
    val bannerNetworkStatus: StateFlow<NetworkResult<BannerResponse>> = _bannerNetworkStatus

    private val _allCosmeticsNetworkStatus: MutableStateFlow<NetworkResult<List<ICosmetic>>> =
        MutableStateFlow(NetworkResult.Ready)
    val allCosmeticsNetworkStatus: StateFlow<NetworkResult<List<ICosmetic>>> =
        _allCosmeticsNetworkStatus

    suspend fun getBattleRoyaleCosmetics() {
        _cosmeticNetworkStatusMap[CosmeticEnum.BATTLE_ROYALE]!!.emit(NetworkResult.Loading)
        delay(2_000L)
        val brCosmeticsResponse: NetworkResult<BattleRoyaleCosmeticsResponse> =
            fortniteApi.callApi<BattleRoyaleCosmeticsResponse>(
                call = { fortniteApi.getBattleRoyaleCosmetics(language = language) },
                responseConverter = responseConverter
            )
        if (brCosmeticsResponse is NetworkResult.Success) {
            _cosmeticNetworkStatusMap[CosmeticEnum.BATTLE_ROYALE]!!.emit(
                NetworkResult.Success(brCosmeticsResponse.data.data.sortedByDescending { it.added })
            )
        } else if (brCosmeticsResponse is NetworkResult.Error) {
            _cosmeticNetworkStatusMap[CosmeticEnum.BATTLE_ROYALE]!!.emit(
                NetworkResult.Error(
                    brCosmeticsResponse.exception,
                    brCosmeticsResponse.status
                )
            )
        }
    }

    suspend fun getBanners() {
        if (_bannerNetworkStatus.value is NetworkResult.Ready ||
            _bannerNetworkStatus.value is NetworkResult.Error
        ) {
            _bannerNetworkStatus.emit(NetworkResult.Loading)
            _bannerNetworkStatus.emit(
                fortniteApi.callApi<BannerResponse>(
                    call = { fortniteApi.getBanners(language = language) },
                    responseConverter = responseConverter
                )
            )
        }
    }

    suspend fun getAllCosmetics() {
        if ((_allCosmeticsNetworkStatus.value is NetworkResult.Ready) ||
            (_allCosmeticsNetworkStatus.value is NetworkResult.Error)
        ) {
            _allCosmeticsNetworkStatus.emit(NetworkResult.Loading)
            for (cosmetic in CosmeticEnum.entries.filter { it != CosmeticEnum.NEW }) {
                _cosmeticNetworkStatusMap[cosmetic]!!.emit(NetworkResult.Loading)
            }
            val allCosmeticsResponse: NetworkResult<CosmeticsResponse> =
                fortniteApi.callApi<CosmeticsResponse>(
                    call = { fortniteApi.getCosmetics(language = language) },
                    responseConverter = responseConverter
                )
            if (allCosmeticsResponse is NetworkResult.Success) {
                _allCosmeticsNetworkStatus.emit(NetworkResult.Success(allCosmeticsResponse.data.data.getCosmetics()))
                _cosmeticNetworkStatusMap[CosmeticEnum.BATTLE_ROYALE]!!.emit(
                    NetworkResult.Success(
                        allCosmeticsResponse.data.data.br?.sortedByDescending { it.added }
                            ?: listOf()))
                _cosmeticNetworkStatusMap[CosmeticEnum.LEGO]!!.emit(
                    NetworkResult.Success(
                        allCosmeticsResponse.data.data.lego?.sortedByDescending { it.added }
                            ?: listOf()))
                _cosmeticNetworkStatusMap[CosmeticEnum.LEGO_KITS]!!.emit(
                    NetworkResult.Success(
                        allCosmeticsResponse.data.data.legoKits?.sortedByDescending { it.added }
                            ?: listOf()))
                _cosmeticNetworkStatusMap[CosmeticEnum.CARS]!!.emit(
                    NetworkResult.Success(
                        allCosmeticsResponse.data.data.cars?.sortedByDescending { it.added }
                            ?: listOf()))
                _cosmeticNetworkStatusMap[CosmeticEnum.TRACKS]!!.emit(
                    NetworkResult.Success(
                        allCosmeticsResponse.data.data.tracks?.sortedByDescending { it.added }
                            ?: listOf()))
                _cosmeticNetworkStatusMap[CosmeticEnum.BEANS]!!.emit(
                    NetworkResult.Success(
                        allCosmeticsResponse.data.data.beans?.sortedByDescending { it.added }
                            ?: listOf()))
                _cosmeticNetworkStatusMap[CosmeticEnum.INSTRUMENTS]!!.emit(
                    NetworkResult.Success(
                        allCosmeticsResponse.data.data.instruments?.sortedByDescending { it.added }
                            ?: listOf()))
            } else if (allCosmeticsResponse is NetworkResult.Error) {
                val exception = allCosmeticsResponse.exception
                val status = allCosmeticsResponse.status
                _allCosmeticsNetworkStatus.emit(
                    NetworkResult.Error(
                        exception = exception,
                        status = status
                    )
                )
                for (cosmetic in CosmeticEnum.entries.filter { it != CosmeticEnum.NEW }) {
                    _cosmeticNetworkStatusMap[cosmetic]!!.emit(
                        NetworkResult.Error(
                            exception = exception,
                            status = status
                        )
                    )
                }
            }
        }
    }

    suspend fun getBeanCosmetics() {
        if ((_cosmeticNetworkStatusMap[CosmeticEnum.BEANS]!!.value is NetworkResult.Ready) ||
            (_cosmeticNetworkStatusMap[CosmeticEnum.BEANS]!!.value is NetworkResult.Error)
        ) {
            _cosmeticNetworkStatusMap[CosmeticEnum.BEANS]!!.emit(NetworkResult.Loading)
            val beanCosmeticsResponse: NetworkResult<BeansCosmeticsResponse> =
                fortniteApi.callApi<BeansCosmeticsResponse>(
                    call = { fortniteApi.getBeanCosmetics(language = language) },
                    responseConverter = responseConverter
                )
            if (beanCosmeticsResponse is NetworkResult.Success) {
                _cosmeticNetworkStatusMap[CosmeticEnum.BEANS]!!.emit(
                    NetworkResult.Success(
                        beanCosmeticsResponse.data.data.sortedByDescending { it.added })
                )
            } else if (beanCosmeticsResponse is NetworkResult.Error) {
                _cosmeticNetworkStatusMap[CosmeticEnum.BEANS]!!.emit(
                    NetworkResult.Error(
                        exception = beanCosmeticsResponse.exception,
                        status = beanCosmeticsResponse.status
                    )
                )
            }
        }
    }

    suspend fun getInstrumentsCosmetics() {
        if ((_cosmeticNetworkStatusMap[CosmeticEnum.INSTRUMENTS]!!.value is NetworkResult.Ready) ||
            (_cosmeticNetworkStatusMap[CosmeticEnum.INSTRUMENTS]!!.value is NetworkResult.Error)
        ) {
            _cosmeticNetworkStatusMap[CosmeticEnum.INSTRUMENTS]!!.emit(NetworkResult.Loading)
            val legoCosmeticResult: NetworkResult<InstrumentsCosmeticsResponse> =
                fortniteApi.callApi<InstrumentsCosmeticsResponse>(
                    call = { fortniteApi.getInstrumentsCosmetics(language = language) },
                    responseConverter = responseConverter
                )
            if (legoCosmeticResult is NetworkResult.Success) {
                _cosmeticNetworkStatusMap[CosmeticEnum.INSTRUMENTS]!!.emit(
                    NetworkResult.Success(
                        legoCosmeticResult.data.data.sortedByDescending { it.added })
                )
            } else if (legoCosmeticResult is NetworkResult.Error) {
                _cosmeticNetworkStatusMap[CosmeticEnum.INSTRUMENTS]!!.emit(legoCosmeticResult)
            }
        }
    }

    suspend fun getLegoCosmetics() {
        if ((_cosmeticNetworkStatusMap[CosmeticEnum.LEGO]!!.value is NetworkResult.Ready) ||
            (_cosmeticNetworkStatusMap[CosmeticEnum.LEGO]!!.value is NetworkResult.Error)
        ) {
            _cosmeticNetworkStatusMap[CosmeticEnum.LEGO]!!.emit(NetworkResult.Loading)
            val legoCosmeticResult: NetworkResult<LegoCosmeticsResponse> =
                fortniteApi.callApi<LegoCosmeticsResponse>(
                    call = { fortniteApi.getLegoCosmetics(language = language) },
                    responseConverter = responseConverter
                )
            if (legoCosmeticResult is NetworkResult.Success) {
                _cosmeticNetworkStatusMap[CosmeticEnum.LEGO]!!.emit(
                    NetworkResult.Success(
                        legoCosmeticResult.data.data.sortedByDescending { it.added })
                )
            } else if (legoCosmeticResult is NetworkResult.Error) {
                _cosmeticNetworkStatusMap[CosmeticEnum.LEGO]!!.emit(
                    legoCosmeticResult
                )
            }
        }
    }

    suspend fun getLegoKitCosmetics() {
        if ((_cosmeticNetworkStatusMap[CosmeticEnum.LEGO_KITS]!!.value is NetworkResult.Ready) ||
            (_cosmeticNetworkStatusMap[CosmeticEnum.LEGO_KITS]!!.value is NetworkResult.Error)
        ) {
            _cosmeticNetworkStatusMap[CosmeticEnum.LEGO_KITS]!!.emit(NetworkResult.Loading)
            val legoKitCosmeticResult: NetworkResult<LegoKitsCosmeticsResponse> =
                fortniteApi.callApi<LegoKitsCosmeticsResponse>(
                    call = { fortniteApi.getLegoCosmetics(language = language) },
                    responseConverter = responseConverter
                )
            if (legoKitCosmeticResult is NetworkResult.Success) {
                _cosmeticNetworkStatusMap[CosmeticEnum.LEGO_KITS]!!.emit(
                    NetworkResult.Success(
                        legoKitCosmeticResult.data.data.sortedByDescending { it.added })
                )
            } else if (legoKitCosmeticResult is NetworkResult.Error) {
                _cosmeticNetworkStatusMap[CosmeticEnum.LEGO_KITS]!!.emit(
                    legoKitCosmeticResult
                )
            }
        }
    }

    suspend fun getTracksCosmetics() {
        if ((_cosmeticNetworkStatusMap[CosmeticEnum.TRACKS]!!.value is NetworkResult.Ready) ||
            (_cosmeticNetworkStatusMap[CosmeticEnum.TRACKS]!!.value is NetworkResult.Error)
        ) {
            _cosmeticNetworkStatusMap[CosmeticEnum.TRACKS]!!.emit(NetworkResult.Loading)
            val tracksCosmeticsResult: NetworkResult<TracksCosmeticsResponse> =
                fortniteApi.callApi<TracksCosmeticsResponse>(
                    call = { fortniteApi.getTracksCosmetics(language = language) },
                    responseConverter = responseConverter
                )
            if (tracksCosmeticsResult is NetworkResult.Success) {
                _cosmeticNetworkStatusMap[CosmeticEnum.TRACKS]!!.emit(
                    NetworkResult.Success(
                        tracksCosmeticsResult.data.data.sortedByDescending { it.added })
                )
            } else if (tracksCosmeticsResult is NetworkResult.Error) {
                _cosmeticNetworkStatusMap[CosmeticEnum.TRACKS]!!.emit(
                    tracksCosmeticsResult
                )
            }
        }
    }

    suspend fun getCarsCosmetics() {
        if ((_cosmeticNetworkStatusMap[CosmeticEnum.CARS]!!.value is NetworkResult.Ready) ||
            (_cosmeticNetworkStatusMap[CosmeticEnum.CARS]!!.value is NetworkResult.Error)
        ) {
            _cosmeticNetworkStatusMap[CosmeticEnum.CARS]!!.emit(NetworkResult.Loading)
            val carsCosmeticResult: NetworkResult<CarsCosmeticsResponse> =
                fortniteApi.callApi<CarsCosmeticsResponse>(
                    call = { fortniteApi.getCarsCosmetics(language = language) },
                    responseConverter = responseConverter
                )
            if (carsCosmeticResult is NetworkResult.Success) {
                _cosmeticNetworkStatusMap[CosmeticEnum.CARS]!!.emit(
                    NetworkResult.Success(
                        carsCosmeticResult.data.data.sortedByDescending { it.added })
                )
            } else if (carsCosmeticResult is NetworkResult.Error) {
                _cosmeticNetworkStatusMap[CosmeticEnum.CARS]!!.emit(
                    carsCosmeticResult
                )
            }
        }
    }

    suspend fun getNewCosmetics() {
        if ((_cosmeticNetworkStatusMap[CosmeticEnum.NEW]!!.value is NetworkResult.Ready) ||
            (_cosmeticNetworkStatusMap[CosmeticEnum.NEW]!!.value is NetworkResult.Error)
        ) {
            _cosmeticNetworkStatusMap[CosmeticEnum.NEW]!!.emit(NetworkResult.Loading)
            val newCosmeticResult: NetworkResult<NewCosmeticsResponse> =
                fortniteApi.callApi<NewCosmeticsResponse>(
                    call = { fortniteApi.getNewCosmetics(language = language) },
                    responseConverter = responseConverter
                )
            if (newCosmeticResult is NetworkResult.Success) {
                _cosmeticNetworkStatusMap[CosmeticEnum.NEW]!!.emit(
                    NetworkResult.Success(
                        newCosmeticResult.data.data.items.getCosmetics()
                            .sortedByDescending { it.getDate() })
                )
            } else if (newCosmeticResult is NetworkResult.Error) {
                _cosmeticNetworkStatusMap[CosmeticEnum.NEW]!!.emit(
                    newCosmeticResult
                )
            }
        }
    }
}