package org.smartmuseum.fortnitecompanion.networking


import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode

suspend inline fun <reified T> FortniteApiInterface.callApi(
    call: suspend FortniteApiInterface.() -> HttpResponse,
    responseConverter: ResponseConverter
): NetworkResult<T> {
    var response: HttpResponse? = null
    try {
        response = this.call()
    } catch (e: Exception) {
        NetworkResult.Error(e, response?.status ?: HttpStatusCode.InternalServerError)
    }
    if (response == null) {
        return NetworkResult.Error(
            Exception("Response is null"),
            HttpStatusCode.InternalServerError
        )
    } else {
        return try {
            responseConverter.convertResponse(response)
        } catch (e: Exception) {
            NetworkResult.Error(e, response.status)
        }
    }
}