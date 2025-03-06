package org.smartmuseum.fortnitecompanion.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diamondedge.logging.KmLog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf
import org.smartmuseum.fortnitecompanion.data.cosmetics.CosmeticEnum
import org.smartmuseum.fortnitecompanion.data.cosmetics.ICosmetic
import org.smartmuseum.fortnitecompanion.networking.NetworkResult
import org.smartmuseum.fortnitecompanion.usecases.GetCosmeticsUseCase

class CosmeticsViewModel : ViewModel(), KoinComponent {
    val kmLog: KmLog by inject { parametersOf("CosmeticsViewModel") }
    val refresh: HashMap<CosmeticEnum, () -> Unit> = hashMapOf()
    private val getCosmeticsUseCase: GetCosmeticsUseCase by inject()
    val cosmeticNetworkStatusMap: HashMap<CosmeticEnum, StateFlow<NetworkResult<List<ICosmetic>>>> =
        getCosmeticsUseCase.cosmeticNetworkStatusMap

    private val _fullDetailCosmetic = MutableStateFlow<ICosmetic?>(null)
    val fullDetailCosmetic: StateFlow<ICosmetic?> = _fullDetailCosmetic
    val bannerNetworkStatus = getCosmeticsUseCase.bannerNetworkStatus

    init {
        kmLog.i { "init instance ${this.toString()}" }
        refresh[CosmeticEnum.NEW] = ::getNewCosmetics
        refresh[CosmeticEnum.BATTLE_ROYALE] = ::getBattleRoyaleCosmetics
        refresh[CosmeticEnum.CARS] = ::getCarsCosmetics
        refresh[CosmeticEnum.LEGO] = ::getLegoCosmetics
        refresh[CosmeticEnum.LEGO_KITS] = ::getLegoKitsCosmetics
        refresh[CosmeticEnum.BEANS] = ::getBeanCosmetics
        refresh[CosmeticEnum.TRACKS] = ::getTracksCosmetics
        refresh[CosmeticEnum.INSTRUMENTS] = ::getInstrumentsCosmetics
    }

    fun getBanners() {
        viewModelScope.launch(Dispatchers.IO) {
            getCosmeticsUseCase.getBanners()
        }
    }

    fun getBattleRoyaleCosmetics() {
        viewModelScope.launch(Dispatchers.IO) {
            getCosmeticsUseCase.getAllCosmetics()
        }
    }

    fun getTracksCosmetics() {
        viewModelScope.launch(Dispatchers.IO) {
            getCosmeticsUseCase.getAllCosmetics()
        }
    }

    fun getLegoCosmetics() {
        viewModelScope.launch(Dispatchers.IO) {
            getCosmeticsUseCase.getAllCosmetics()
        }
    }

    fun getLegoKitsCosmetics() {
        viewModelScope.launch(Dispatchers.IO) {
            getCosmeticsUseCase.getAllCosmetics()
        }
    }

    fun getBeanCosmetics() {
        viewModelScope.launch(Dispatchers.IO) {
            getCosmeticsUseCase.getAllCosmetics()
        }
    }

    fun getInstrumentsCosmetics() {
        viewModelScope.launch(Dispatchers.IO) {
            getCosmeticsUseCase.getAllCosmetics()
        }
    }

    fun getCarsCosmetics() {
        viewModelScope.launch(Dispatchers.IO) {
            getCosmeticsUseCase.getAllCosmetics()
        }
    }

    fun getNewCosmetics() {
        viewModelScope.launch(Dispatchers.IO) {
            getCosmeticsUseCase.getNewCosmetics()
        }
    }

    suspend fun setFullCosmetic(newFullCosmetic: ICosmetic?) {
        _fullDetailCosmetic.emit(newFullCosmetic)
    }
}
