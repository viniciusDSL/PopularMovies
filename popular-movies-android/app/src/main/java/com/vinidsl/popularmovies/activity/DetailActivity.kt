package com.vinidsl.popularmovies.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.vinidsl.popular_movies_sdk.model.Movie
import com.vinidsl.popular_movies_sdk.util.Utils
import com.vinidsl.popularmovies.R

import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*
import kotlinx.android.synthetic.main.description_layout.*

class DetailActivity : AppCompatActivity() {

    companion object {

        const val PARAM_NAME = "movie"

        fun launchDetail(context: Context, movie: Movie){
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(PARAM_NAME, movie)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)

        val movie : Movie = intent.getParcelableExtra(PARAM_NAME)
        Glide.with(this).load(Utils.getPosterUrl(movie.posterPath)).into(imageViewMoviePoster)

        textViewTitle.text = movie.title
        textViewReleaseDate.text = movie.releaseDate
        textVoteAverage.text = movie.voteAverage.toString()

    }

}
