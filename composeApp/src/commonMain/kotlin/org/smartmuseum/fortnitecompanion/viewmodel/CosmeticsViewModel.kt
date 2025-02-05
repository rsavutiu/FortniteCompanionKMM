package org.smartmuseum.fortnitecompanion.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.smartmuseum.fortnitecompanion.data.cosmetics.BattleRoyaleCosmeticsResponse
import org.smartmuseum.fortnitecompanion.data.cosmetics.BeanCosmetic
import org.smartmuseum.fortnitecompanion.data.cosmetics.BeansCosmeticsResponse
import org.smartmuseum.fortnitecompanion.data.cosmetics.CarCosmetic
import org.smartmuseum.fortnitecompanion.data.cosmetics.CarsCosmeticsResponse
import org.smartmuseum.fortnitecompanion.data.cosmetics.Cosmetic
import org.smartmuseum.fortnitecompanion.data.cosmetics.CosmeticsResponse
import org.smartmuseum.fortnitecompanion.data.cosmetics.InstrumentCosmetic
import org.smartmuseum.fortnitecompanion.data.cosmetics.LegoCosmetic
import org.smartmuseum.fortnitecompanion.data.cosmetics.LegoCosmeticsResponse
import org.smartmuseum.fortnitecompanion.data.cosmetics.LegoKitCosmetic
import org.smartmuseum.fortnitecompanion.data.cosmetics.LegoKitsCosmeticsResponse
import org.smartmuseum.fortnitecompanion.data.cosmetics.TrackCosmetic
import org.smartmuseum.fortnitecompanion.data.cosmetics.TracksCosmeticsResponse
import org.smartmuseum.fortnitecompanion.networking.FortniteApiInterface
import org.smartmuseum.fortnitecompanion.networking.NetworkResult
import org.smartmuseum.fortnitecompanion.networking.ResponseConverter
import org.smartmuseum.fortnitecompanion.networking.callApi

class CosmeticsViewModel(private val fortniteApi: FortniteApiInterface) : ViewModel(),
    KoinComponent {
    private val _allCosmetics = MutableStateFlow<List<Cosmetic>>(emptyList())
    val allCosmetics: StateFlow<List<Cosmetic>> = _allCosmetics
    private val _battleRoyaleCosmetics = MutableStateFlow<List<Cosmetic>>(emptyList())
    val battleRoyaleCosmetics: StateFlow<List<Cosmetic>> = _battleRoyaleCosmetics
    private val _carsCosmetics = MutableStateFlow<List<CarCosmetic>>(emptyList())
    val carsCosmetics: StateFlow<List<CarCosmetic>> = _carsCosmetics
    private val _legoCosmetics = MutableStateFlow<List<LegoCosmetic>>(emptyList())
    val legoCosmetics: StateFlow<List<LegoCosmetic>> = _legoCosmetics
    private val _tracksCosmetics = MutableStateFlow<List<TrackCosmetic>>(emptyList())
    val tracksCosmetics: StateFlow<List<TrackCosmetic>> = _tracksCosmetics
    private val _beansCosmetics = MutableStateFlow<List<BeanCosmetic>>(emptyList())
    val beansCosmetics: StateFlow<List<BeanCosmetic>> = _beansCosmetics
    private val _instrumentsCosmetics = MutableStateFlow<List<InstrumentCosmetic>>(emptyList())
    val instrumentsCosmetics: StateFlow<List<InstrumentCosmetic>> = _instrumentsCosmetics
    private val _legoKitsCosmetics = MutableStateFlow<List<LegoKitCosmetic>>(emptyList())
    val legoKitsCosmetics: StateFlow<List<LegoKitCosmetic>> = _legoKitsCosmetics
    private val _networkError = MutableStateFlow(false)
    val networkError: StateFlow<Boolean> = _networkError
    private val responseConverter: ResponseConverter by inject()

    fun getAllCosmetics() {
        viewModelScope.launch(Dispatchers.IO) {
            val allCosmetics: NetworkResult<CosmeticsResponse> =
                fortniteApi.callApi<CosmeticsResponse>(
                    call = { fortniteApi.getCosmetics() },
                    responseConverter = responseConverter
                )
            if (allCosmetics is NetworkResult.Success) {
                _battleRoyaleCosmetics.emit(allCosmetics.data.data.br)
                _legoKitsCosmetics.emit(allCosmetics.data.data.legoKits)
                _legoCosmetics.emit(allCosmetics.data.data.lego)
                _carsCosmetics.emit(allCosmetics.data.data.cars)
                _tracksCosmetics.emit(allCosmetics.data.data.tracks)
                _beansCosmetics.emit(allCosmetics.data.data.beans)
                _instrumentsCosmetics.emit(allCosmetics.data.data.instruments)
                _networkError.emit(true)
            } else {
                _networkError.emit(false)
            }
        }
    }

    fun getBattleRoyaleCosmetics() {
        viewModelScope.launch(Dispatchers.IO) {
            val brCosmetics: NetworkResult<BattleRoyaleCosmeticsResponse> =
                fortniteApi.callApi<BattleRoyaleCosmeticsResponse>(
                    call = { fortniteApi.getBattleRoyaleCosmetics() },
                    responseConverter = responseConverter
                )
            if (brCosmetics is NetworkResult.Success) {
                _battleRoyaleCosmetics.emit(brCosmetics.data.data)
                _networkError.emit(true)
            } else {
                _networkError.emit(false)
            }
        }
    }

    fun getBeanCosmetics() {
        viewModelScope.launch(Dispatchers.IO) {
            val beanCosmetics: NetworkResult<BeansCosmeticsResponse> =
                fortniteApi.callApi<BeansCosmeticsResponse>(
                    call = { fortniteApi.getBeanCosmetics() },
                    responseConverter = responseConverter
                )
            if (beanCosmetics is NetworkResult.Success) {
                _beansCosmetics.emit(beanCosmetics.data.data)
                _networkError.emit(true)
            } else {
                _networkError.emit(false)
            }
        }
    }

    fun getLegoCosmetics() {
        viewModelScope.launch(Dispatchers.IO) {
            val legoCosmeticResult: NetworkResult<LegoCosmeticsResponse> =
                fortniteApi.callApi<LegoCosmeticsResponse>(
                    call = { fortniteApi.getLegoCosmetics() },
                    responseConverter = responseConverter
                )
            if (legoCosmeticResult is NetworkResult.Success) {
                _legoCosmetics.emit(legoCosmeticResult.data.data)
                _networkError.emit(true)
            } else {
                _networkError.emit(false)
            }
        }
    }

    fun getLegoKitCosmetics() {
        viewModelScope.launch(Dispatchers.IO) {
            val legoKitCosmeticResult: NetworkResult<LegoKitsCosmeticsResponse> =
                fortniteApi.callApi<LegoKitsCosmeticsResponse>(
                    call = { fortniteApi.getLegoCosmetics() },
                    responseConverter = responseConverter
                )
            if (legoKitCosmeticResult is NetworkResult.Success) {
                _legoKitsCosmetics.emit(legoKitCosmeticResult.data.data)
                _networkError.emit(true)
            } else {
                _networkError.emit(false)
            }
        }
    }

    fun getTracksCosmetics() {
        viewModelScope.launch(Dispatchers.IO) {
            val tracksCosmeticsResult: NetworkResult<TracksCosmeticsResponse> =
                fortniteApi.callApi<TracksCosmeticsResponse>(
                    call = { fortniteApi.getTracksCosmetics() },
                    responseConverter = responseConverter
                )
            if (tracksCosmeticsResult is NetworkResult.Success) {
                _tracksCosmetics.emit(tracksCosmeticsResult.data.data)
                _networkError.emit(false)
            } else {
                _networkError.emit(true)
            }
        }
    }

    fun getCarsCosmetics() {
        viewModelScope.launch(Dispatchers.IO) {
            val carsCosmeticResult: NetworkResult<CarsCosmeticsResponse> =
                fortniteApi.callApi<CarsCosmeticsResponse>(
                    call = { fortniteApi.getCarsCosmetics() },
                    responseConverter = responseConverter
                )
            if (carsCosmeticResult is NetworkResult.Success) {
                _carsCosmetics.emit(carsCosmeticResult.data.data)
                _networkError.emit(false)
            } else {
                _networkError.emit(true)
            }
        }
    }
}
