package org.smartmuseum.fortnitecompanion.networking


import io.ktor.client.statement.HttpResponse

suspend inline fun <reified T> FortniteApiInterface.callApi(
    call: suspend FortniteApiInterface.() -> HttpResponse,
    responseConverter: ResponseConverter
): NetworkResult<T> {
    return try {
        val response = this.call()
        responseConverter.convertResponse(response)
    } catch (e: Exception) {
        NetworkResult.Error(e)
    }
}