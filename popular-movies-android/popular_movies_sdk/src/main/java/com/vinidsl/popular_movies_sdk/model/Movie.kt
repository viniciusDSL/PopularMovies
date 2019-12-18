package com.vinidsl.popular_movies_sdk.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "movie")
data class Movie internal constructor (

    @SerializedName("poster_path")
    var posterPath: String,

    @PrimaryKey
    @SerializedName("id")
    var id: Long,

    @SerializedName("title")
    var title: String,

    @SerializedName("vote_average")
    var voteAverage: Double,

    @SerializedName("release_date")
    var releaseDate: String,

    @SerializedName("popularity")
    var popularity: Double,

    @SerializedName("vote_count")
    var voteCount: Int,

    @SerializedName("adult")
    var forAdults: Boolean
) : Parcelable