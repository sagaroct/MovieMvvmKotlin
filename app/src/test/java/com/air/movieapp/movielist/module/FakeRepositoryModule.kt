/*
package com.air.movieapp.movielist.module

import com.air.movieapp.data.network.IMoviesRepository
import com.air.movieapp.di.NetworksModule
import com.air.movieapp.di.RepositoryModule
import com.air.movieapp.movielist.FakeMovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [FragmentComponent::class],
    replaces = [RepositoryModule::class]
)
abstract class FakeRepositoryModule {

  @Binds
  abstract fun bindFakeMovieRepository(
    fakeMovieRepository: FakeMovieRepository
  ): IMoviesRepository


}*/
