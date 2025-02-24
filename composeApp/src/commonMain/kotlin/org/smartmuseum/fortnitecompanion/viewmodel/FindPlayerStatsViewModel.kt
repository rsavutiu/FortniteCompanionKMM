package org.smartmuseum.fortnitecompanion.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.smartmuseum.fortnitecompanion.usecases.FindStatsResult
import org.smartmuseum.fortnitecompanion.usecases.FindStatsUseCase

class FindPlayerStatsViewModel : ViewModel(), KoinComponent {
    private val findStatsUseCase: FindStatsUseCase by inject()
    private val _playerQuery = MutableStateFlow("") // Non-nullable, default to empty string
    val playerQuery: StateFlow<String> = _playerQuery
    var statsResult: StateFlow<FindStatsResult?> = findStatsUseCase.statsResult

    init {
        viewModelScope.launch {
            playerQuery.onEach { name ->
                if (name.isNotBlank()) {
                    findStatsUseCase.loadStat(name = name)
                }
            }.stateIn(viewModelScope, SharingStarted.Lazily, "").collect {}
        }
    }

    fun setPlayerQuery(query: String) {
        _playerQuery.value = query
    }
}