package com.air.movieapp.view.movielist.dependency

import com.air.movieapp.view.movielist.adapter.MovieListAdapter
import dagger.Module
import dagger.Provides

/**
 * Created by sagar on 10/8/17.
 */
@Module
class MovieListModule {

    @Provides
    fun provideMovieListAdapter(): MovieListAdapter {
        return MovieListAdapter(ArrayList())
    }

}