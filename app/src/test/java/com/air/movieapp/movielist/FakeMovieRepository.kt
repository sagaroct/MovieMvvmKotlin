package com.air.movieapp.movielist

import com.air.movieapp.common.Constants
import com.air.movieapp.common.RestConstants
import com.air.movieapp.data.model.Movie
import com.air.movieapp.data.model.Results
import com.air.movieapp.data.network.IMoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

/**
 * Created by Sagar Pujari on 24/01/23.
 */
class FakeMovieRepository : IMoviesRepository {

    private val results = Results(0, listOf(
        Movie(
            0,"someTitle", "1992-12-10", "soneOverView", 1.00f, Constants.POPULAR
        )
    ))

    override fun getMoviesFromApi(category: String, page: Int): Flow<List<Movie>> {
        return flow { emit(results) }
            .map { saveMovieList(category, it) }
            .catch { emptyList<Movie>() }
            .flowOn(Dispatchers.Unconfined)
    }

    override suspend fun saveMovieList(category: String, results: Results): List<Movie> {
        return results.movies
    }

    override suspend fun getMoviesFromDb(category: String): List<Movie> {
        return emptyList()
    }

    override fun setCategoryToMovies(categoryName: String?, movies: List<Movie>) {
    }
}