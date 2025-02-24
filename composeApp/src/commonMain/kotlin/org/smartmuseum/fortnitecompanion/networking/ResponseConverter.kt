package org.smartmuseum.fortnitecompanion.networking

import de.jensklingenberg.ktorfit.converter.Converter
import io.ktor.client.statement.*
import io.ktor.client.call.*
import io.ktor.http.*
import kotlinx.serialization.SerializationException

sealed class NetworkResult<out T> {
    object Ready : NetworkResult<Nothing>()
    object Loading : NetworkResult<Nothing>()
    class Success<T>(val data: T) : NetworkResult<T>()
    class Error(val exception: Exception, val status: HttpStatusCode) : NetworkResult<Nothing>()
}

class ResponseConverter: Converter.Factory {
    suspend inline fun <reified T> convertResponse(response: HttpResponse): NetworkResult<T> {
        return try {
            when (response.status) {
                HttpStatusCode.OK -> {
                    val data: T = response.body()
                    NetworkResult.Success(data)
                }
                else -> {
                    val error = response.body<String>()
                    NetworkResult.Error(
                        exception = Exception("HTTP ${response.status.value}: $error"),
                        status = response.status
                    )
                }
            }
        } catch (e: SerializationException) {
            NetworkResult.Error(e, response.status)
        } catch (e: Exception) {
            NetworkResult.Error(e, response.status)
        }
    }
}