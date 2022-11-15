package com.dominik55.moviebrowser.data.network

import com.dominik55.moviebrowser.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = BuildConfig.API_KEY
        val request = chain.request()
        val newUrl = request
            .url
            .newBuilder()
            .addQueryParameter("api_key", token)
            .build()

        return chain.proceed(
            request.newBuilder()
                .url(newUrl)
                .build()
        )
    }
}