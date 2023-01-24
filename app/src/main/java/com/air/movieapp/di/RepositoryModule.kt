package com.air.movieapp.di

import com.air.movieapp.data.network.IMoviesRepository
import com.air.movieapp.data.network.MoviesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent
import kotlinx.coroutines.Dispatchers

/**
 * Created by Sagar Pujari on 24/01/23.
 */
@Module
@InstallIn(FragmentComponent::class)
abstract class RepositoryModule {

    //TODO: Maybe here we need to bind FakeRepository as well.
    @Binds
    abstract fun bindMovieRepository(
        analyticsServiceImpl: MoviesRepository
    ): IMoviesRepository

}
