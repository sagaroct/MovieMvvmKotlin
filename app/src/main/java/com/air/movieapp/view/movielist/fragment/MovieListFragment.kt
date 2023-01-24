package com.air.movieapp.view.movielist.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.air.movieapp.R
import com.air.movieapp.common.Constants
import com.air.movieapp.common.Constants.TOP_RATED
import com.air.movieapp.data.model.Movie
import com.air.movieapp.data.network.MoviesRepository
import com.air.movieapp.databinding.FragmentMovieBinding
import com.air.movieapp.view.movielist.adapter.MovieListAdapter
import com.air.movieapp.view.movielist.viewmodel.MovieListViewModel
import com.air.movieapp.view.movielist.viewmodel.MovieListViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named

/**
 * Common fragment for all movie listing
 */
@AndroidEntryPoint
class MovieListFragment : Fragment() {
    private lateinit var mMovieListViewModel: MovieListViewModel
    private lateinit var mFragmentMovieBinding: FragmentMovieBinding

    lateinit var mMovieListAdapter: MovieListAdapter

    @Inject
    lateinit var mMoviesRepository: MoviesRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(TAG, "onCreateView: called")
        mFragmentMovieBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_movie, container, false)
        val view: View = mFragmentMovieBinding.getRoot()
        initViewModel()
        setAdapter()
        applyObserver()
        loadData()
        return view
    }

    private fun initViewModel(){
        val category = arguments?.getString(CATEGORY) ?: TOP_RATED
        val factory = MovieListViewModelFactory(mMoviesRepository, category)
        mMovieListViewModel = ViewModelProvider(this, factory).get(MovieListViewModel::class.java)
        mFragmentMovieBinding.setMovieListViewModel(mMovieListViewModel)
    }

    private fun setAdapter(){
        mMovieListAdapter = MovieListAdapter(arrayListOf<Movie>())
        mFragmentMovieBinding.rvMovie.adapter = mMovieListAdapter
    }

    private fun loadData() {
        mMovieListViewModel.load()
    }

    private fun applyObserver() {
        mMovieListViewModel.moviesLiveData.observe(viewLifecycleOwner) { movies ->
            Log.d(TAG, "onChanged: called: Movies : ${movies.size}")
            mMovieListAdapter.setData(movies)
        }
    }

    fun filter(searchText: String) {
        mMovieListAdapter.filter.filter(searchText)
    }

    fun sortBy(sortType: Constants.SortType?) {
        mMovieListViewModel.sortBy(sortType)
    }

    companion object {
        private const val TAG: String = "MovieListFragment"
        private const val CATEGORY: String = "category"

        fun newInstance(category: String): MovieListFragment {
            val movieListFragment =  MovieListFragment()
            val args = Bundle()
            args.putString(CATEGORY, category)
            movieListFragment.arguments = args
            return movieListFragment
        }
    }
}