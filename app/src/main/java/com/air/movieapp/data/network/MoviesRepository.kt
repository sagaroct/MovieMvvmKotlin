package com.air.movieapp.data.network

import android.util.Log
import com.air.movieapp.common.RestConstants
import com.air.movieapp.data.database.MovieDatabase
import com.air.movieapp.data.model.Movie
import com.air.movieapp.data.model.Results
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

/**
 * Created by sagar on 4/8/17.
 */
 class MoviesRepository(movieApiService: MovieApiService, movieDatabase: MovieDatabase) {

    private val localSource = movieDatabase.movieDao()
    private val networkService = movieApiService

    fun getMoviesFromApi(category: String, page: Int): Flow<List<Movie>> {
        return flow { emit(networkService.getMovies(category, RestConstants.AP_KEY, page)) }
            .map { saveMovieList(category, it) }
            .catch { getMoviesFromDb(category) }
            .flowOn(Dispatchers.IO)
    }

    private suspend fun saveMovieList(category: String, results: Results): List<Movie> {
        setCategoryToMovies(category, results.movies)
        localSource.insertOrUpdate(results.movies)
        return getMoviesFromDb(category)
    }

    private suspend fun getMoviesFromDb(category: String): List<Movie> {
        val movies = localSource.getMoviesByCategory(category)
        Log.e("MovieListViewModel","movies $movies")
        return movies

    }

    private fun setCategoryToMovies(categoryName: String?, movies: List<Movie>) {
        for (movie in movies) {
            movie.type = categoryName
        }
    }

    companion object {
        private const val TAG = "MoviesRepository"
    }

}