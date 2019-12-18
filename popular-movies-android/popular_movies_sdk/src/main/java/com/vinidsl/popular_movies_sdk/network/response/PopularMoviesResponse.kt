package com.vinidsl.popular_movies_sdk.network.response

import com.google.gson.annotations.SerializedName
import com.vinidsl.popular_movies_sdk.model.Movie

class PopularMoviesResponse(var page: Int,
                            @SerializedName("total_results")
                            var totalResults: Int,
                            @SerializedName("total_pages")
                            var totalPages: Int,
                            @SerializedName("results")
                            var result: List<Movie>)