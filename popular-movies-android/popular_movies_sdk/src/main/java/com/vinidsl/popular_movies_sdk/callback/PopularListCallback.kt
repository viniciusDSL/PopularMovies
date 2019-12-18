package com.vinidsl.popular_movies_sdk.callback

import com.vinidsl.popular_movies_sdk.model.Movie

interface PopularListCallback : BaseSDKCallback {
    fun onComplete(result :List<Movie>)
}