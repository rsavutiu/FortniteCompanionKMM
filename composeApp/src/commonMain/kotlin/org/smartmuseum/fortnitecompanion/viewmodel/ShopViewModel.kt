package org.smartmuseum.fortnitecompanion.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.smartmuseum.fortnitecompanion.data.shop.ShopResponse
import org.smartmuseum.fortnitecompanion.networking.NetworkResult
import org.smartmuseum.fortnitecompanion.usecases.GetShopDataUseCase

class ShopViewModel : ViewModel(), KoinComponent {
    private val getShopDataUseCase: GetShopDataUseCase by inject()
    val shopResult: StateFlow<NetworkResult<ShopResponse>> = getShopDataUseCase.result

    init {
        viewModelScope.launch {
            getShopDataUseCase.load()
        }
    }
}