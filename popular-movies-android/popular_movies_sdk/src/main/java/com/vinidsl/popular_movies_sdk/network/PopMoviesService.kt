package com.vinidsl.popular_movies_sdk.network

import com.vinidsl.popular_movies_sdk.network.response.PopularMoviesResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface PopMoviesService {

    @GET("popular")
    fun getPopular() : Single<Response<PopularMoviesResponse>>
}