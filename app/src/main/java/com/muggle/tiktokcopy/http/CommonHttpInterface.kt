package com.muggle.tiktokcopy.http

import android.util.Log
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.request

object CommonHttpInterface {

    private const val TAG = "CommHttpInterface"

    suspend fun <T> startHttpRequest(
        httpRequestBuilder: HttpRequestBuilder,
        onBeforeRequest: () -> Unit = {},
        onRequestSuccess: () -> Unit = {},
        onRequestError: (Throwable) -> Unit = {},
        onRequestComplete: () -> Unit = {}
    ): Result<CommonResponse<T>> {
        return runCatching {
            onBeforeRequest()
            val result =
                HttpClientProvider.client.request(httpRequestBuilder).body() as CommonResponse<T>
            onRequestSuccess()
            onRequestComplete()
            result
        }.onFailure {
            Log.e(TAG, "startHttpRequest: ", it)
            onRequestError(it)
            onRequestComplete()
        }
    }
}