package com.air.movieapp.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.air.movieapp.common.Constants
import com.air.movieapp.commonutils.MockConstants
import com.air.movieapp.data.database.MovieDao
import com.air.movieapp.data.database.MovieDatabase
import com.air.movieapp.data.network.MovieApiService
import com.air.movieapp.data.network.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by Sagar Pujari on 24/01/23.
 * */

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieRepositoryTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var moviesRepository: MoviesRepository
    @Mock
    private lateinit var movieApiService: MovieApiService
    @Mock
    private lateinit var movieDatabase: MovieDatabase

    @Before
    fun createRepository() {
        MockitoAnnotations.openMocks(this)
        // Get a reference to the class under test
        moviesRepository = MoviesRepository(
            // TODO Dispatchers.Unconfined should be replaced with Dispatchers.Main
            //  this requires understanding more about coroutines + testing
            //  so we will keep this as Unconfined for now.
            movieApiService, movieDatabase, Dispatchers.Unconfined
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getMoviesFromApiSuccessTest() =
        runTest {
            Mockito.`when`(movieApiService.getMovies(Constants.POPULAR, "dummykey", page = 1))
                .thenReturn( MockConstants.MOCK_RESULTS )
            Mockito.`when`(moviesRepository.getMoviesFromDb(Constants.POPULAR))
                .thenReturn(MockConstants.MOCK_RESULTS.movies)
            val value = moviesRepository.getMoviesFromApi(Constants.POPULAR, page = 1).firstOrNull()
            assertEquals(MockConstants.MOCK_RESULTS.movies, value)
        }
}
