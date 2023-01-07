package com.air.movieapp.movielist;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.Observer;

import com.air.movieapp.common.Constants;
import com.air.movieapp.data.model.Movie;
import com.air.movieapp.data.model.Results;
import com.air.movieapp.data.network.MoviesRepository;
import com.air.movieapp.data.network.NetworkUtils;
import com.air.movieapp.data.network.ResponseCallback;
import com.air.movieapp.view.movielist.viewmodel.MovieListViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sagar on 14/12/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class MovieListViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    private List<Movie> MOVIES = Arrays.asList(new Movie("someTitle", "1992-12-10"
        , "soneOverView", 1.00f, Constants.POPULAR ));

    private Results results = new Results();

    private static List<Movie> EMPTY_MOVIES = new ArrayList<>(0);

    @Mock
    private NetworkUtils mNetworkUtils;

    @Mock
    private MoviesRepository mMoviesRepository;

    @Captor
    private ArgumentCaptor<ResponseCallback> mLoadMoviesCallbackCaptor;

    private MovieListViewModel movieListViewModel;

    @Mock
    private Observer<List<Movie>> observer;

    @Mock
    private Observer<Boolean> mProgresShow;

    @Before
    public void setupMovieListPresenter() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this);

        // Get a reference to the class under test
        movieListViewModel = new MovieListViewModel(mMoviesRepository, Constants.POPULAR, 1);
    }

    @Test
    public void loadMoviesFromRepositoryAndLoadIntoView() {
        // Given an initialized MovieListPresenter with initialized movies
        // When loading of Movies is requested
        when(mNetworkUtils.isNetworkConnected()).thenReturn(true);
        movieListViewModel.getMoviesFromNetwork(Constants.POPULAR, 1);
        // Callback is captured and invoked with stubbed movies
        verify(mMoviesRepository, atMost(2)).getMoviesLiveData(eq(Constants.POPULAR), eq(1), mLoadMoviesCallbackCaptor.capture());
        results.setMovies(MOVIES);
        mLoadMoviesCallbackCaptor.getValue().successFromNetwork(results);
        movieListViewModel.getMoviesLiveData().observeForever(observer);
        verify(observer, atMost(2)).onChanged((MOVIES));
    }

    @Test
    public void loadEmptyMoviesIntoView() {
        when(mNetworkUtils.isNetworkConnected()).thenReturn(true);
        // Given an initialized MovieListPresenter with initialized movies
        // When loading of Movies is requested
        movieListViewModel.getMoviesFromNetwork(Constants.POPULAR, 1);

        // Callback is captured and invoked with stubbed movies
        verify(mMoviesRepository).getMovies(eq(Constants.POPULAR), eq(1), mLoadMoviesCallbackCaptor.capture());
        results.setMovies(EMPTY_MOVIES);
        mLoadMoviesCallbackCaptor.getValue().successFromNetwork(results);

        // Then progress indicator is hidden and emptyview is shown in UI.
        verify(mProgresShow).onChanged(true);
    }



}
