package com.vinidsl.popularmovies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vinidsl.popular_movies_sdk.model.Movie
import com.vinidsl.popular_movies_sdk.util.Utils
import com.vinidsl.popularmovies.R
import com.vinidsl.popularmovies.activity.DetailActivity

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var popularMovies = emptyList<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false))
    }

    override fun getItemCount(): Int {
        return popularMovies.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = popularMovies[position]
        val url = Utils.getPosterUrl(item.posterPath)
        Glide.with(holder.itemView.context).load(url).into(holder.imageViewMoviePoster)
        holder.textViewTitle.text = item.title
        holder.textViewReleaseDate.text = item.releaseDate
        holder.textVoteAverage.text = item.voteAverage.toString()
        holder.itemView.setOnClickListener {
            DetailActivity.launchDetail(holder.itemView.context, popularMovies[position])
        }
    }

    class MovieViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val imageViewMoviePoster = view.findViewById(R.id.imageViewMoviePoster) as ImageView
        val textViewTitle = view.findViewById(R.id.textViewTitle) as TextView
        val textVoteAverage = view.findViewById(R.id.textVoteAverage) as TextView
        val textViewReleaseDate = view.findViewById(R.id.textViewReleaseDate) as TextView
    }

    fun setData(popularMovies: List<Movie>){
        this.popularMovies = popularMovies
        notifyDataSetChanged()
    }
}