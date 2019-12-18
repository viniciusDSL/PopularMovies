package com.vinidsl.popularmovies.viewmodel

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vinidsl.popular_movies_sdk.PopMovies
import com.vinidsl.popular_movies_sdk.callback.PopularListCallback
import com.vinidsl.popular_movies_sdk.error.PopMoviesError
import com.vinidsl.popular_movies_sdk.model.Movie
import com.vinidsl.popularmovies.PopMovieApp

class MainListViewModel(applicationContext: Application) : AndroidViewModel(applicationContext) {

    private var movieList = MutableLiveData<List<Movie>>()

    fun getMoviePreviewList(): LiveData<List<Movie>>? {
        return movieList
    }

    fun getDataFromServer(){
        (getApplication() as PopMovieApp).POP_MOVIES.getPopularMovies(object : PopularListCallback {
            override fun onComplete(result: List<Movie>) {
                movieList.postValue(result)
            }

            override fun onError(error: PopMoviesError) {
                Toast.makeText(getApplication(),error.message,Toast.LENGTH_SHORT).show()
            }
        })
    }

}
