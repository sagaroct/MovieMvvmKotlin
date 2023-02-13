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
 class MoviesRepository @Inject constructor(private val movieApiService: MovieApiService,
   private val movieDatabase: MovieDatabase, private val dispatcher: CoroutineDispatcher = Dispatchers.IO) : IMoviesRepository {

    override fun getMoviesFromApi(category: String, page: Int): Flow<List<Movie>> {
        return flow { emit(movieApiService.getMovies(category, Constants.RestConstants.AP_KEY, page)) }
            .map { saveMovieList(category, it) }
            .catch { emit(getMoviesFromDb(category)) }
            .flowOn(dispatcher)
    }

    private suspend fun saveMovieList(category: String, results: Results): List<Movie> {
        setCategoryToMovies(category, results.movies)
        movieDatabase.movieDao().insertOrUpdate(results.movies)
        return getMoviesFromDb(category)
    }

    override suspend fun getMoviesFromDb(category: String): List<Movie> {
        val movies = movieDatabase.movieDao().getMoviesByCategory(category)
        Log.d(TAG,"getMoviesFromDb: $movies")
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