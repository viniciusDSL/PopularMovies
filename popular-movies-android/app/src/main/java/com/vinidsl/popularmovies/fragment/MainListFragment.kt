package com.vinidsl.popularmovies.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.HORIZONTAL
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import com.vinidsl.popularmovies.viewmodel.MainListViewModel
import com.vinidsl.popularmovies.R
import com.vinidsl.popularmovies.adapter.MovieAdapter
import kotlinx.android.synthetic.main.loading_view.*
import kotlinx.android.synthetic.main.main_list_fragment.*


class MainListFragment : Fragment() {

    private lateinit var viewModel: MainListViewModel
    private lateinit var movieAdapter : MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewManager = LinearLayoutManager(context)

        val itemDecor = DividerItemDecoration(context, VERTICAL)

        popularMoviesRecycler.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            addItemDecoration(itemDecor)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainListViewModel::class.java)

        movieAdapter = MovieAdapter()
        popularMoviesRecycler.adapter = movieAdapter

        viewModel.getMoviePreviewList()?.observe(this, Observer { moviesPreview ->
            // update UI
            movieAdapter.setData(moviesPreview)
            loadingView.visibility = View.GONE
        })

        viewModel.getDataFromServer()
    }

}
