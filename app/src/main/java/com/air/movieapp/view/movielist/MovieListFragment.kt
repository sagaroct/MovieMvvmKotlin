/*
 *
 *  * Copyright © 2016, Robosoft Technologies Pvt. Ltd
 *  * Written under contract by Robosoft Technologies Pvt. Ltd.
 *
 */
package com.air.movieapp.view.movielist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.air.movieapp.MovieApplication
import com.air.movieapp.R
import com.air.movieapp.adapter.movielist.MovieListAdapter
import com.air.movieapp.common.Constants
import com.air.movieapp.databinding.FragmentMovieBinding
import com.air.movieapp.model.Movie
import com.air.movieapp.network.MoviesRepository
import com.air.movieapp.view.base.BaseFragment
import com.air.movieapp.viewmodel.MovieListViewModel
import com.air.movieapp.viewmodel.MovieListViewModelFactory
import javax.inject.Inject
import javax.inject.Named

/**
 * Common fragment for all movie listing
 */
class MovieListFragment : BaseFragment() {
    private var mType: String? = null
    private var mMovieListViewModel: MovieListViewModel? = null
    private var mFragmentMovieBinding: FragmentMovieBinding? = null

    @Inject
    lateinit var mMovieListAdapter: MovieListAdapter

    @field:[Inject Named("SimpleService")] lateinit var mMoviesRepository: MoviesRepository

    override fun onCreate(state: Bundle?) {
        super.onCreate(state)
        mType = arguments?.getString(Constants.TAB)
    }

    override fun setupFragmentComponent() {
        MovieApplication.get(getActivity())?.appComponent?.plus(MovieListModule(getActivity()))?.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(TAG, "onCreateView: called")
        mFragmentMovieBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie, container, false)
        val view: View = mFragmentMovieBinding!!.getRoot()
        val factory = MovieListViewModelFactory(mMoviesRepository, mType, 1)
        mMovieListViewModel = ViewModelProviders.of(this, factory).get(MovieListViewModel::class.java)
        mFragmentMovieBinding!!.setMovieListViewModel(mMovieListViewModel)
        initAdapter()
        movieList
        return view
    }

    private val movieList: Unit
        get() {
            mMovieListViewModel?.moviesLiveData?.observe(this, object : Observer<List<Movie>> {
                override fun onChanged(movies: List<Movie>) {
                    Log.d(TAG, "onChanged: called")
                    mMovieListAdapter!!.setData(movies)
                }
            })
        }

    fun initAdapter() {
        val mLinearLayoutManager = LinearLayoutManager(getActivity())
        mFragmentMovieBinding?.rvMovie?.setItemAnimator(DefaultItemAnimator())
        mFragmentMovieBinding?.rvMovie?.setLayoutManager(mLinearLayoutManager)
        mFragmentMovieBinding?.rvMovie?.setAdapter(mMovieListAdapter)
    }

    fun filter(searchText: String?) {
        mMovieListAdapter?.getFilter()?.filter(searchText)
    }

    fun sortBy(sortType: Constants.SortType?) {
        mMovieListViewModel!!.sortBy(sortType)
    }

    companion object {
        private val TAG: String? = MovieListFragment::class.java.getSimpleName()
    }
}