package com.vinidsl.popular_movies_sdk.network

import com.vinidsl.popular_movies_sdk.PopMovies
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url = request.url.newBuilder().addQueryParameter("api_key", PopMovies.apiKey).build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}