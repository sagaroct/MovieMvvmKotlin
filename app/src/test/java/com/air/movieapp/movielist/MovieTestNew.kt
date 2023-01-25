package com.air.movieapp.movielist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.air.movieapp.common.Constants.POPULAR
import com.air.movieapp.data.model.Movie
import com.air.movieapp.data.network.NetworkUtils
import com.air.movieapp.view.movielist.viewmodel.MovieListViewModel
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

/**
 * Created by sagar on 14/12/17.
 */


/*@RunWith(RobolectricTestRunner::class)
@Config(sdk = [30], application = HiltTestApplication::class)
@HiltAndroidTest*/
//@Config(sdk = [30])
@RunWith(AndroidJUnit4::class)
class MovieTestNew {
    @Rule
    var instantExecutorRule = InstantTaskExecutorRule()

//    @get:Rule var hiltrule = HiltAndroidRule(this)
    @Mock
    private lateinit var mNetworkUtils: NetworkUtils
    private lateinit var mMoviesRepository: FakeMovieRepository
    private lateinit var movieListViewModel: MovieListViewModel
    private val movies = listOf(
        Movie(
            0,"someTitle", "1992-12-10", "soneOverView", 1.00f, POPULAR
        )
    )

    @Before
    fun setupMovieListPresenter() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
//        hiltrule.inject()
        MockitoAnnotations.initMocks(this)
        // Get a reference to the class under test
        mMoviesRepository = FakeMovieRepository()
        movieListViewModel = MovieListViewModel(mMoviesRepository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun loadMoviesFromRepositoryAndLoadIntoView() =
        runTest {
            // Given an initialized MovieListPresenter with initialized movies
            // When loading of Movies is requested
            Mockito.`when`(mNetworkUtils.isNetworkConnected).thenReturn(true)
            Mockito.`when`(mMoviesRepository.getMoviesFromApi(POPULAR, 1)).thenReturn(flow { movies })
            val value = movieListViewModel.getMoviesFromNetwork(POPULAR, page = 1)
//            assertEquals(value.size, 1)
            // Callback is captured and invoked with stubbed movies
            /*Mockito.verify(mMoviesRepository, Mockito.atMost(2)).getMoviesFromApi(
                    POPULAR,
                1
            )*/
//            results.movies = movies
           /* movieListViewModel.moviesLiveData.observeForever(observer)
            Mockito.verify(observer, Mockito.atMost(2)).onChanged(
                movies
            )*/
        }


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
    }*/

    companion object {
        private val EMPTY_MOVIES: List<Movie> = ArrayList(0)
    }
}