package com.air.movieapp

import android.app.Application
import android.content.Context
import com.air.movieapp.data.DataModule
import com.air.movieapp.network.NetworkModule
import com.air.movieapp.view.movielist.MovieListComponent
import com.air.movieapp.view.movielist.MovieListModule
import kotlin.jvm.Throws

/**
 * Created by sagar on 4/8/17.
 */
class MovieApplication : Application() {
    var mAppComponent: AppComponent? = null
    var mMovieListComponent: MovieListComponent? = null

    override fun onCreate() {
        super.onCreate()
        mAppComponent = DaggerAppComponent.builder()
                .networkModule(NetworkModule(getApplicationContext()))
                .dataModule(DataModule(getApplicationContext()))
                .build()
    }

    val appComponent: com.air.movieapp.AppComponent?
        get() = mAppComponent

    fun createMovieListComponent(context: Context?): MovieListComponent? {
        if (mMovieListComponent == null) {
            mMovieListComponent = mAppComponent!!.plus(MovieListModule(context))
        }
        return mMovieListComponent
    }

    fun releaseMovieListComponent() {
        mMovieListComponent = null
    }

    companion object {
        operator fun get(context: Context?): MovieApplication? {
            return context?.getApplicationContext() as MovieApplication
        }
    }
}