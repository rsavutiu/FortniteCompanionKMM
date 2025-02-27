package org.smartmuseum.fortnitecompanion.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.smartmuseum.fortnitecompanion.usecases.GetShopDataUseCase
import org.smartmuseum.fortnitecompanion.usecases.GetShopResult

class ShopViewModel : ViewModel(), KoinComponent {
    private val getShopDataUseCase: GetShopDataUseCase by inject()
    val shopResult: StateFlow<GetShopResult> = getShopDataUseCase.shopResult

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getShopDataUseCase.loadShop()
        }
    }
}