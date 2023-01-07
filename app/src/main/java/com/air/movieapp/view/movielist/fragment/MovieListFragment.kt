package com.air.movieapp.view.movielist.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.air.movieapp.MovieApplication
import com.air.movieapp.R
import com.air.movieapp.view.movielist.adapter.MovieListAdapter
import com.air.movieapp.common.Constants
import com.air.movieapp.common.Constants.TOP_RATED
import com.air.movieapp.databinding.FragmentMovieBinding
import com.air.movieapp.data.network.MoviesRepository
import com.air.movieapp.view.base.BaseFragment
import com.air.movieapp.view.movielist.dependency.MovieListModule
import com.air.movieapp.view.movielist.viewmodel.MovieListViewModel
import com.air.movieapp.view.movielist.viewmodel.MovieListViewModelFactory
import javax.inject.Inject
import javax.inject.Named

/**
 * Common fragment for all movie listing
 */
class MovieListFragment : BaseFragment() {
    private lateinit var mMovieListViewModel: MovieListViewModel
    private lateinit var mFragmentMovieBinding: FragmentMovieBinding

    @Inject
    lateinit var mMovieListAdapter: MovieListAdapter

    @field:[Inject Named("SimpleService")]
    lateinit var mMoviesRepository: MoviesRepository

    override fun setupFragmentComponent() {
        MovieApplication.get(requireActivity()).appComponent?.plus(MovieListModule(requireActivity()))
            ?.inject(this)
    }

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
        val factory = MovieListViewModelFactory(mMoviesRepository, category, 1)
        mMovieListViewModel = ViewModelProvider(this, factory).get(MovieListViewModel::class.java)
        mFragmentMovieBinding.setMovieListViewModel(mMovieListViewModel)
    }

    private fun setAdapter(){
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

    fun filter(searchText: String?) {
        mMovieListAdapter.getFilter().filter(searchText)
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