package com.vinidsl.popular_movies_sdk

import android.content.Context
import com.vinidsl.popular_movies_sdk.callback.PopularListCallback
import com.vinidsl.popular_movies_sdk.error.PopMoviesError
import com.vinidsl.popular_movies_sdk.repository.MovieRepository

class PopMovies private constructor(builder: Builder){

    companion object {
        internal var apiKey: String? = null
        internal var debugEnable: Boolean = false
        internal var storeLocalData: Boolean = false
        internal var context: Context? = null
        internal var posterDefaultWidth = 200
    }

    private lateinit var movieRepository: MovieRepository

    class Builder {
        var apiKey: String? = null ; private set
        var debugEnable = true ; private set
        var posterDefaultWidth = 200 ; private set
        var storeLocalData = true ; private set
        var context : Context? = null ; private set

        fun apiKey(apiKey: String) = apply { this.apiKey = apiKey }
        fun debugEnable(debugEnable: Boolean) = apply { this.debugEnable = debugEnable }
        fun posterDefaultWidth(posterDefaultWidth: Int) = apply { this.posterDefaultWidth = posterDefaultWidth }
        fun storeLocalData(storeLocalData: Boolean) = apply { this.storeLocalData = storeLocalData }
        fun context(context: Context) = apply { this.context = context }
        fun build() = PopMovies(this)
    }

    init {

        builder.apiKey?.let {
            apiKey = it
        } ?: throw PopMoviesError("You must initialize the sdk passing the api key value on the builder")

        builder.context?.let {
            context = it
            movieRepository = MovieRepository(it)
        } ?: throw PopMoviesError("You must initialize the sdk passing the Application Context on the builder")

        debugEnable = builder.debugEnable
        storeLocalData = builder.storeLocalData
        posterDefaultWidth = builder.posterDefaultWidth
    }

    fun getPopularMovies(callback: PopularListCallback) {
        movieRepository.getPopularMovies(callback)
    }
}