package com.air.movieapp

import com.air.movieapp.data.DataModule
import com.air.movieapp.network.NetworkModule
import com.air.movieapp.view.movielist.MovieListComponent
import com.air.movieapp.view.movielist.MovieListModule
import javax.inject.Singleton
import dagger.Component
import kotlin.jvm.Throws

@Singleton
@Component(modules = [NetworkModule::class, DataModule::class])
interface AppComponent {
     fun plus(movieListModule: MovieListModule): MovieListComponent
}