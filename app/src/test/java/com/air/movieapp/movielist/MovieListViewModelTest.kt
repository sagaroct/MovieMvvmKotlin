/*
package com.air.movieapp.movielist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.air.movieapp.common.Constants.POPULAR
import com.air.movieapp.data.model.Movie
import com.air.movieapp.data.model.Results
import com.air.movieapp.data.network.MoviesRepository
import com.air.movieapp.data.network.NetworkUtils
import com.air.movieapp.view.movielist.viewmodel.MovieListViewModel
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.junit.MockitoJUnitRunner
import org.robolectric.annotation.Config
import java.util.*

*/
/**
 * Created by sagar on 14/12/17.
 *//*

@Config(sdk = [30])
@RunWith(AndroidJUnit4::class)
class MovieListViewModelTest {
    @Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    private val movies = listOf(
        Movie(
            0,"someTitle", "1992-12-10", "soneOverView", 1.00f, POPULAR
        )
    )
    private val results = Results(0, movies)

    @Mock
    private lateinit var mNetworkUtils: NetworkUtils

    @Mock
    private lateinit var mMoviesRepository: MoviesRepository

    private lateinit var movieListViewModel: MovieListViewModel

   */
/* @Mock
    private lateinit var observer: Observer<List<Movie>>*//*


    @Mock
    private lateinit var mProgresShow: Observer<Boolean>

    @Before
    fun setupMovieListPresenter() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this)
        // Get a reference to the class under test
        movieListViewModel = MovieListViewModel(mMoviesRepository, POPULAR)
    }

    @Test
    fun loadMoviesFromRepositoryAndLoadIntoView() {
        runBlocking<Unit> {
            // Given an initialized MovieListPresenter with initialized movies
            // When loading of Movies is requested
            Mockito.`when`(mNetworkUtils.isNetworkConnected).thenReturn(true)
            Mockito.`when`(mMoviesRepository.getMoviesFromApi(POPULAR, 1)).thenReturn(flow { movies })
            val value = movieListViewModel.getMoviesFromNetwork(page = 1)
            assertEquals(value, movies)
            // Callback is captured and invoked with stubbed movies
            */
/*Mockito.verify(mMoviesRepository, Mockito.atMost(2)).getMoviesFromApi(
                    POPULAR,
                1
            )*//*

//            results.movies = movies
           */
/* movieListViewModel.moviesLiveData.observeForever(observer)
            Mockito.verify(observer, Mockito.atMost(2)).onChanged(
                movies
            )*//*

        }
    }

    */
/*@Test
    fun loadEmptyMoviesIntoView() {
        Mockito.`when`(mNetworkUtils.isNetworkConnected).thenReturn(true)
        // Given an initialized MovieListPresenter with initialized movies
        // When loading of Movies is requested
        movieListViewModel.getMoviesFromNetwork(POPULAR, 1)

        // Callback is captured and invoked with stubbed movies
        Mockito.verify(mMoviesRepository).getMovies(
            ArgumentMatchers.eq(POPULAR),
            ArgumentMatchers.eq(1),
            mLoadMoviesCallbackCaptor!!.capture()
        )
        results.movies = EMPTY_MOVIES
        mLoadMoviesCallbackCaptor!!.value.successFromNetwork(results)

        // Then progress indicator is hidden and emptyview is shown in UI.
        Mockito.verify(mProgresShow).onChanged(true)
    }*//*


    companion object {
        private val EMPTY_MOVIES: List<Movie> = ArrayList(0)
    }
}*/