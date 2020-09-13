package com.air.movieapp.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.air.movieapp.network.MoviesRepository

/**
 * Created by sagar on 5/10/17.
 */
class MovieListViewModelFactory(moviesRepository: MoviesRepository?, category: String?, page: Int) : ViewModelProvider.Factory {
    private val mMoviesRepository: MoviesRepository?
    private val mCategory: String?
    private val mPage: Int


    init {
        mMoviesRepository = moviesRepository
        mCategory = category
        mPage = page
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun <T : ViewModel?> create(aClass: Class<T>): T {
        if (aClass.isAssignableFrom(MovieListViewModel::class.java)) {
            return MovieListViewModel(mMoviesRepository, mCategory, mPage) as T
        }
        throw IllegalArgumentException("Unknown class name")    }
}