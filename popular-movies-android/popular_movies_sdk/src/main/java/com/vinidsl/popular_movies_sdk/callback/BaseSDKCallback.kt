package com.vinidsl.popular_movies_sdk.callback

import com.vinidsl.popular_movies_sdk.error.PopMoviesError

interface BaseSDKCallback {

    fun onError(error : PopMoviesError)

}