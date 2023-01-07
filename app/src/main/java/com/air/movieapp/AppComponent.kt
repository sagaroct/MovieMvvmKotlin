package com.air.movieapp

import com.air.movieapp.data.network.NetworkModule
import com.air.movieapp.view.movielist.dependency.MovieListComponent
import com.air.movieapp.view.movielist.dependency.MovieListModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface AppComponent {
     fun plus(movieListModule: MovieListModule): MovieListComponent
}