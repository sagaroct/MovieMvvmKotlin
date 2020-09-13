package com.air.movieapp.network

import android.util.Log
import com.air.movieapp.common.RestConstants
import com.air.movieapp.data.DatabaseHelper
import com.air.movieapp.model.Results

/**
 * Created by sagar on 4/8/17.
 */
class MoviesRepository(movieApiService: MovieApiService?, networkUtils: NetworkUtils?, databaseHelper: DatabaseHelper?) {
    private val mMovieApiService: MovieApiService?
    private val mNetworkUtils: NetworkUtils?
    private val mDatabaseHelper: DatabaseHelper?
    private var mCacheType: CacheType?
    fun setCacheType(cacheType: CacheType?) {
        mCacheType = cacheType
    }

   /* fun getMovies(category: String?, page: Int, callback: ResponseCallback?) {
        when (mCacheType) {
            CacheType.NETWORK -> mMovieApiService!!.getMovies(category, RestConstants.AP_KEY, page).enqueue(callback)
            CacheType.CACHE -> {
                val movies: List<Movie?> = mDatabaseHelper!!.getMovies(category)
                if (movies == null || movies.isEmpty()) {
                    mMovieApiService!!.getMovies(category, RestConstants.AP_KEY, page).enqueue(callback)
                } else {
                    fetchMoviesFromCache(movies, page, callback)
                }
            }
            CacheType.NETWORK_AND_CACHE -> {
                val movies1: List<Movie?> = mDatabaseHelper!!.getMovies(category)
                if (movies1 != null && !movies1.isEmpty()) {
                    fetchMoviesFromCache(movies1, page, callback)
                }
                mMovieApiService!!.getMovies(category, RestConstants.AP_KEY, page).enqueue(callback)
            }
        }
    }

    private fun fetchMoviesFromCache(movies: List<Movie?>?, page: Int, callback: ResponseCallback?) {
        if (page > 1) return
        val results = Results()
        results.setMovies(movies)
        callback.successFromDatabase(results)
    }*/

    fun getMoviesLiveData(category: String?, page: Int, callback: ResponseCallback<Results?>) {
        Log.d(TAG, "getMoviesLiveData: called")
        mMovieApiService?.getMovies(category, RestConstants.AP_KEY, page)?.enqueue(callback)
    }

    companion object {
        private val TAG: String? = "Service"
    }

    init {
        mMovieApiService = movieApiService
        mNetworkUtils = networkUtils
        mDatabaseHelper = databaseHelper
        mCacheType = CacheType.NETWORK_AND_CACHE
    }
}