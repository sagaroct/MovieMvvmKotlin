package com.air.movieapp.viewmodel

import android.os.Build
import android.text.TextUtils
import androidx.annotation.RequiresApi
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.air.movieapp.common.Constants
import com.air.movieapp.model.Movie
import com.air.movieapp.model.Results
import com.air.movieapp.network.MoviesRepository
import com.air.movieapp.network.NetworkError
import com.air.movieapp.network.ResponseCallback
import java.util.Collections
import java.util.Comparator
import retrofit2.Call

/**
 * Created by sagar on 26/9/17.
 */
class MovieListViewModel @RequiresApi(api = Build.VERSION_CODES.N) constructor(moviesRepository: MoviesRepository?, category: String?, page: Int) : ViewModel() {
  
    private val listMutableLiveData: MutableLiveData<List<Movie>> = MutableLiveData()
    private val mMoviesRepository: MoviesRepository?
    var mProgresShow: ObservableBoolean? = ObservableBoolean(false)

    /**
     * Expose the LiveData Movies query so the UI can observe it.
     */
    val moviesLiveData: LiveData<List<Movie>>
        get() = listMutableLiveData

    fun sortBy(sortType: Constants.SortType?) {
        when (sortType) {
            Constants.SortType.TITLE -> {
                Collections.sort(listMutableLiveData.getValue(), object : Comparator<Movie> {

                    override fun compare(m1: Movie, m2: Movie): Int {
                        if(TextUtils.isEmpty(m1.title) || TextUtils.isEmpty(m2.title)){
                            return 0
                        }
                        return m1.title?.compareTo(m2.title!!) ?: 0
                    }
                })
                listMutableLiveData.setValue(listMutableLiveData.getValue())
            }
        }
    }

    fun getMoviesFromNetwork(category: String?, page: Int) {
//        Log.d("MovieListViewModel", "getMoviesFromNetwork: ");
        mProgresShow?.set(true)
        mMoviesRepository!!.getMoviesLiveData(category, page, object : ResponseCallback<Results?>() {
            override fun successFromNetwork(results: Results?) {
                mProgresShow?.set(false)
                listMutableLiveData.setValue(results?.movies)
            }

            override fun successFromDatabase(results: Results?) {
                mProgresShow?.set(false)
                listMutableLiveData.setValue(results?.movies)
            }


            override fun failure(call: Call<Results?>?, error: NetworkError?) {
                mProgresShow?.set(false)
            }

            override fun onTimeOut(call: Call<Results?>?) {
                mProgresShow?.set(false)
            }
        })
    }

    init {
        mMoviesRepository = moviesRepository
        getMoviesFromNetwork(category, page)
    }
}