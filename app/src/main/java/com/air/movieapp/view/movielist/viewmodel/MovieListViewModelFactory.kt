package com.air.movieapp.view.movielist.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.air.movieapp.data.network.IMoviesRepository

/**
 * Created by sagar on 5/10/17.
 */
class MovieListViewModelFactory(
    private val moviesRepository: IMoviesRepository,
    private val category: String
) : ViewModelProvider.Factory {

    @RequiresApi(Build.VERSION_CODES.N)
    override fun <T : ViewModel> create(aClass: Class<T>): T {
        if (aClass.isAssignableFrom(MovieListViewModel::class.java)) {
            return MovieListViewModel(moviesRepository, category) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}