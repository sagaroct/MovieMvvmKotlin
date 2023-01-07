package com.air.movieapp.view.movielist.viewmodel

import android.os.Build
import android.text.TextUtils
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.air.movieapp.common.Constants
import com.air.movieapp.data.model.Movie
import com.air.movieapp.data.network.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.Collections
import java.util.Comparator

/**
 * Created by sagar on 26/9/17.
 */
class MovieListViewModel @RequiresApi(api = Build.VERSION_CODES.N)
constructor(private val moviesRepository: MoviesRepository, private val category: String, private val page: Int) : ViewModel() {
  
    private val listMutableLiveData: MutableLiveData<List<Movie>> = MutableLiveData()
    var mProgressShow: ObservableBoolean = ObservableBoolean(false)

    /**
     * Expose the LiveData Movies query so the UI can observe it.
     */
    val moviesLiveData: LiveData<List<Movie>>
        get() = listMutableLiveData

    fun load() {
        mProgressShow.set(true)
        viewModelScope.launch(Dispatchers.IO) {
            val movies = getMoviesFromNetwork(category, page)
            Log.d("MovieListViewModel", "movies: $movies")
            listMutableLiveData.postValue(movies)
            mProgressShow.set(false)
        }
    }

    private suspend fun getMoviesFromNetwork(category: String, page: Int): List<Movie> {
        return moviesRepository.getMoviesFromApi(category, page)
            .retryWhen { _, attempt -> attempt < 3 }
            .catch { error ->
                Log.e("MovieListViewModel","Catch ${error.message}")
                emit(listOf())
            }.firstOrNull() ?: emptyList()
    }

    fun sortBy(sortType: Constants.SortType?) {
        when (sortType) {
            Constants.SortType.TITLE -> {
                Collections.sort(listMutableLiveData.value, object : Comparator<Movie> {
                    override fun compare(m1: Movie, m2: Movie): Int {
                        if(TextUtils.isEmpty(m1.title) || TextUtils.isEmpty(m2.title)){
                            return 0
                        }
                        return m1.title?.compareTo(m2.title!!) ?: 0
                    }
                })
                listMutableLiveData.setValue(listMutableLiveData.getValue())
            }
            else -> {
                //TODO: Do nothing for now.
            }
        }
    }

}