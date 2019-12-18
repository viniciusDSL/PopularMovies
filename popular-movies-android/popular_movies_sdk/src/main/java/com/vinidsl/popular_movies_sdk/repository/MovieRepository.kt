package com.vinidsl.popular_movies_sdk.repository

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.os.Looper
import com.vinidsl.popular_movies_sdk.PopMovies
import com.vinidsl.popular_movies_sdk.callback.PopularListCallback
import com.vinidsl.popular_movies_sdk.database.AppDatabase
import com.vinidsl.popular_movies_sdk.error.PopMoviesError
import com.vinidsl.popular_movies_sdk.network.ApiKeyInterceptor
import com.vinidsl.popular_movies_sdk.network.PopMoviesService
import com.vinidsl.popular_movies_sdk.network.response.PopularMoviesResponse
import com.vinidsl.popular_movies_sdk.util.Constants
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class MovieRepository(context: Context) {

    private var dataBase: AppDatabase
    private var popMoviesAPI: PopMoviesService

    init {
        val client = OkHttpClient.Builder().addInterceptor(ApiKeyInterceptor())

        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.apply { logInterceptor.level = HttpLoggingInterceptor.Level.BODY }

        client.addInterceptor(logInterceptor)

        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client.build())
            .addCallAdapterFactory(
                RxJava2CallAdapterFactory.create()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        popMoviesAPI = retrofit.create(PopMoviesService::class.java)
        dataBase = AppDatabase.createPersistentDatabase(context)
    }

    fun getPopularMovies(callback: PopularListCallback) {
        if (PopMovies.storeLocalData) {
            getPopularFromLocalDB(callback)
        } else {
            fetchMostPopularFromAPI(callback)
        }
    }

    private fun fetchMostPopularFromAPI(callback: PopularListCallback) {

        val testObservable: Single<Response<PopularMoviesResponse>> =
            popMoviesAPI.getPopular()

        testObservable.subscribeOn(Schedulers.io())
            .subscribe(object :
                SingleObserver<Response<PopularMoviesResponse>> {
                override fun onSuccess(response: Response<PopularMoviesResponse>) {
                    if (response.isSuccessful) {
                        for (movie in response.body()!!.result) {
                            dataBase.movieDao().insert(movie)
                        }
                    } else {
                        Handler(Looper.getMainLooper()).post {
                            callback.onError(PopMoviesError(response.message()))
                        }
                    }
                }

                override fun onSubscribe(d: Disposable) {}

                override fun onError(e: Throwable) {
                    Handler(Looper.getMainLooper()).post {
                        callback.onError(
                            PopMoviesError(
                                "Some error happened trying to fetch the API",
                                e
                            )
                        )
                    }

                }
            })
    }

    @SuppressLint("CheckResult")
    private fun getPopularFromLocalDB(callback: PopularListCallback) {

        dataBase.movieDao().getAll()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { movieList ->
                if (movieList.isNullOrEmpty()) {
                    callback.onError(PopMoviesError("There are no movies on the local DB"))
                } else {
                    callback.onComplete(movieList)
                }
                fetchMostPopularFromAPI(callback)
            }
    }

}