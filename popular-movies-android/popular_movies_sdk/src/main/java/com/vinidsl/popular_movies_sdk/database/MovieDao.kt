package com.vinidsl.popular_movies_sdk.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vinidsl.popular_movies_sdk.model.Movie
import io.reactivex.Flowable
import io.reactivex.Single


@Dao
interface MovieDao {

    @Query("SELECT * FROM movie ORDER BY voteAverage DESC")
    fun getAll(): Flowable<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(movie: Movie) : Long

}