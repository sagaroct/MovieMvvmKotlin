package com.air.movieapp.data.network

import android.util.Log
import com.air.movieapp.common.Constants
import com.air.movieapp.data.database.MovieDatabase
import com.air.movieapp.data.model.Movie
import com.air.movieapp.data.model.Results
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

/**
 * Created by sagar on 4/8/17.
 */
 class MoviesRepository @Inject constructor(movieApiService: MovieApiService, movieDatabase: MovieDatabase,
        private val dispatcher: CoroutineDispatcher = Dispatchers.IO) : IMoviesRepository {

     private val localSource = movieDatabase.movieDao()
     private val networkService = movieApiService

    override fun getMoviesFromApi(category: String, page: Int): Flow<List<Movie>> {
        return flow { emit(networkService.getMovies(category, Constants.RestConstants.AP_KEY, page)) }
            .map { saveMovieList(category, it) }
            .catch { emit(getMoviesFromDb(category)) }
            .flowOn(dispatcher)
    }

    override suspend fun saveMovieList(category: String, results: Results): List<Movie> {
        setCategoryToMovies(category, results.movies)
        localSource.insertOrUpdate(results.movies)
        return getMoviesFromDb(category)
    }

    override suspend fun getMoviesFromDb(category: String): List<Movie> {
        val movies = localSource.getMoviesByCategory(category)
        Log.d(TAG,"getMoviesFromDb: $movies")
        return movies

    }

    override fun setCategoryToMovies(categoryName: String?, movies: List<Movie>) {
        for (movie in movies) {
            movie.type = categoryName
        }
    }

    companion object {
        private const val TAG = "MoviesRepository"
    }

}