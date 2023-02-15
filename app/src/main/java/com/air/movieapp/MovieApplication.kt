package com.air.movieapp

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

/**
 * Created by sagar on 4/8/17.
 */
@HiltAndroidApp
class MovieApplication : Application() {

    companion object {
        operator fun get(context: Context?): MovieApplication {
            return context?.applicationContext as MovieApplication
        }
    }

}