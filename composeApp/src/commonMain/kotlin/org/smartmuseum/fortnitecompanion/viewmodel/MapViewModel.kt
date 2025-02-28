package org.smartmuseum.fortnitecompanion.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.smartmuseum.fortnitecompanion.data.map.MapResponse
import org.smartmuseum.fortnitecompanion.networking.NetworkResult
import org.smartmuseum.fortnitecompanion.usecases.GetMapUseCase

class MapViewModel : ViewModel(), KoinComponent {
    private val getMapUseCase: GetMapUseCase by inject()
    val mapResult: StateFlow<NetworkResult<MapResponse>> = getMapUseCase.result

    fun load() {
        viewModelScope.launch {
            getMapUseCase.load()
        }
    }
}