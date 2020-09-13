package com.air.movieapp.view.movielist

import dagger.Subcomponent

/**
 * Created by sagar on 10/8/17.
 */
//@MovieListScope
@Subcomponent(modules = [MovieListModule::class])
interface MovieListComponent {
    fun inject(movieListFragment: MovieListFragment?)
}