package com.vinidsl.popularmovies

import android.app.Application
import com.vinidsl.popular_movies_sdk.PopMovies
import com.vinidsl.popular_movies_sdk.model.Movie

class PopMovieApp : Application() {

    lateinit var POP_MOVIES : PopMovies

    override fun onCreate() {
        super.onCreate()
        POP_MOVIES = PopMovies.Builder()
            .apiKey("b3b7322b9997c86f4555b3e824a25b99")
            .context(this)
            .debugEnable(true)
            .storeLocalData(true)
            .posterDefaultWidth(200)
            .build()
    }
}