package com.vinidsl.popular_movies_sdk.util

import com.vinidsl.popular_movies_sdk.PopMovies

object Utils {

    fun getPosterUrl(posterPath : String, posterWidth: Int = PopMovies.posterDefaultWidth) : String {
        return "${Constants.IMAGES_URL}w$posterWidth$posterPath"
    }
}