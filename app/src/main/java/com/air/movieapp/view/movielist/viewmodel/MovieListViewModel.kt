package com.air.movieapp.view.movielist.viewmodel

import android.text.TextUtils
import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.air.movieapp.common.Constants
import com.air.movieapp.data.model.Movie
import com.air.movieapp.data.network.IMoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.retryWhen
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

/**
 * Created by sagar on 26/9/17.
 */
@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val moviesRepository: IMoviesRepository
) : ViewModel() {
  
    var mProgressShow: ObservableBoolean = ObservableBoolean(false)

    private val listMutableLiveData: MutableLiveData<List<Movie>> = MutableLiveData()
    /**
     * Expose the LiveData Movies query so the UI can observe it.
     */
    val moviesLiveData: LiveData<List<Movie>>
        get() = listMutableLiveData

    fun load(category: String) {
        mProgressShow.set(true)
        viewModelScope.launch(Dispatchers.IO) {
            val movies = getMoviesFromNetwork(category, page = 1)
            listMutableLiveData.postValue(movies)
            mProgressShow.set(false)
        }
    }

    /**
     * For pagination pass the page number here.
     */
    private suspend fun getMoviesFromNetwork(category: String, page: Int): List<Movie> {
        return moviesRepository.getMoviesFromApi(category, page)
            .retryWhen { _, attempt -> attempt < 3 }
            .catch { error ->
                Log.e(TAG,"getMoviesFromNetwork: ${error.message}")
                emit(listOf())
            }.firstOrNull() ?: emptyList()
    }

    fun sortBy(movies: List<Movie>, sortType: Constants.SortType?) {
        when (sortType) {
            Constants.SortType.TITLE -> {
                val sortedMovies = movies.sortedBy { it.title }
                listMutableLiveData.setValue(sortedMovies)
            }
            else -> {
                //TODO: Do nothing for now.
            }
        }
    }

    companion object {
        private const val TAG = "MovieListViewModel"
    }

}