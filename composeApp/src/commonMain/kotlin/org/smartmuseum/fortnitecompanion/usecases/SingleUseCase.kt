package org.smartmuseum.fortnitecompanion.usecases

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.smartmuseum.fortnitecompanion.networking.FortniteApiInterface
import org.smartmuseum.fortnitecompanion.networking.NetworkResult

abstract class SingleUseCase<T>(val fortniteApi: FortniteApiInterface, val language: String?) {
    private var _result = MutableStateFlow<NetworkResult<T>>(NetworkResult.Ready)
    var result: StateFlow<NetworkResult<T>> = _result

    protected abstract suspend fun loadCall(): NetworkResult<T>
    suspend fun load() {
        if (result.value is NetworkResult.Ready || result.value is NetworkResult.Error) {
            _result.emit(NetworkResult.Loading)
            val result = loadCall()
            _result.emit(result)
        }
    }
}