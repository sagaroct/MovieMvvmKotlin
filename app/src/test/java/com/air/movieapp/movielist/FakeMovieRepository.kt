package com.air.movieapp.movielist

import com.air.movieapp.commonutils.MockConstants.MOCK_RESULTS
import com.air.movieapp.data.model.Movie
import com.air.movieapp.data.model.Results
import com.air.movieapp.data.network.IMoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

/**
 * Created by Sagar Pujari on 24/01/23.
 */
class FakeMovieRepository : IMoviesRepository {

    override fun getMoviesFromApi(category: String, page: Int): Flow<List<Movie>> {
        return flow { emit(MOCK_RESULTS.movies) }
            .catch { emptyList<Movie>() }
            .flowOn(Dispatchers.Unconfined)
    }

    override suspend fun getMoviesFromDb(category: String): List<Movie> {
        return emptyList()
    }

}