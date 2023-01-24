/*
package com.air.movieapp.source

import com.air.movieapp.common.Constants
import com.air.movieapp.data.database.MovieDatabase
import com.air.movieapp.data.model.Movie
import com.air.movieapp.data.network.MovieApiService
import com.air.movieapp.data.network.MoviesRepository
import kotlinx.coroutines.Dispatchers
import org.junit.Before

*/
/**
 * Created by Sagar Pujari on 24/01/23.
 *//*

class MovieRepositoryTest {

    private val movies = listOf(
        Movie(
            0,"someTitle", "1992-12-10", "soneOverView", 1.00f, Constants.POPULAR
        )
    )

    private lateinit var moviesRepository: MoviesRepository

    private lateinit var movieApiService: MovieApiService
    private lateinit var movieDatabase: MovieDatabase

    @Before
    fun createRepository() {
        tasksRemoteDataSource = FakeDataSource(remoteTasks.toMutableList())
        tasksLocalDataSource = FakeDataSource(localTasks.toMutableList())
        // Get a reference to the class under test
        moviesRepository = MoviesRepository(
            // TODO Dispatchers.Unconfined should be replaced with Dispatchers.Main
            //  this requires understanding more about coroutines + testing
            //  so we will keep this as Unconfined for now.
            movieApiService, movieDatabase, Dispatchers.Unconfined
        )
    }
}*/
