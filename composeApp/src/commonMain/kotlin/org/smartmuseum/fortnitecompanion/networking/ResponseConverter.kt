package org.smartmuseum.fortnitecompanion.networking

import de.jensklingenberg.ktorfit.converter.Converter
import io.ktor.client.statement.*
import io.ktor.client.call.*
import io.ktor.http.*
import kotlinx.serialization.SerializationException

sealed class NetworkResult<out T> {
    class Success<T>(val data: T) : NetworkResult<T>()
    class Error(val exception: Exception) : NetworkResult<Nothing>()
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
                    NetworkResult.Error(Exception("HTTP ${response.status.value}: $error"))
                }
            }
        } catch (e: SerializationException) {
            NetworkResult.Error(e)
        } catch (e: Exception) {
            NetworkResult.Error(e)
        }
    }
}