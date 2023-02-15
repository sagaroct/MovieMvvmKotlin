package com.air.movieapp.movielist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.air.movieapp.common.Constants
import com.air.movieapp.common.Constants.POPULAR
import com.air.movieapp.commonutils.MockConstants.MOCK_RESULTS
import com.air.movieapp.data.network.IMoviesRepository
import com.air.movieapp.getOrAwaitValue
import com.air.movieapp.view.movielist.viewmodel.MovieListViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by sagar on 14/12/17.
 */

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieListViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
//    private lateinit var mMoviesRepository: FakeMovieRepository
    @Mock
    private lateinit var mMockMoviesRepository: IMoviesRepository
    private lateinit var movieListViewModel: MovieListViewModel

    @Before
    fun setupMovieListPresenter() {
        MockitoAnnotations.openMocks(this)
        // Get a reference to the class under test
//        mMoviesRepository = FakeMovieRepository()
        movieListViewModel = MovieListViewModel(mMockMoviesRepository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun loadMoviesIntoView() =
        runTest {
            Mockito.`when`(mMockMoviesRepository.getMoviesFromApi(POPULAR, page = 1))
                .thenReturn( flow { emit(MOCK_RESULTS.movies) } )
            movieListViewModel.load(POPULAR)
            val value = movieListViewModel.moviesLiveData.getOrAwaitValue()
            print(value)
            assertEquals(MOCK_RESULTS.movies, value)
        }

    @Test
    fun loadEmptyMoviesIntoView() {
            Mockito.`when`(mMockMoviesRepository.getMoviesFromApi(POPULAR, page = 1))
                .thenReturn( flow { emit(emptyList())})
            movieListViewModel.load(POPULAR)
            val value = movieListViewModel.moviesLiveData.getOrAwaitValue()
            print(value)
            assertEquals(true, value.isEmpty())
        }

    @Test
    fun sortByTitleValid() {
            movieListViewModel.sortBy(MOCK_RESULTS.movies, Constants.SortType.TITLE)
            val value = movieListViewModel.moviesLiveData.getOrAwaitValue()
            print(value)
            assertEquals(MOCK_RESULTS.movies.sortedBy { it.title }, value)
        }


}