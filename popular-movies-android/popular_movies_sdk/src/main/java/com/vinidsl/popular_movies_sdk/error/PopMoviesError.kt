package com.vinidsl.popular_movies_sdk.error

class PopMoviesError internal constructor(errorMessage:String,  err: Throwable = Throwable()) : Throwable(errorMessage,  err)