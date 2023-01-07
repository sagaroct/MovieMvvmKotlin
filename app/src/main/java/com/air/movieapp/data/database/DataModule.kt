/*
package com.air.movieapp.data.database

import android.content.Context
import javax.inject.Singleton
import dagger.Module
import dagger.Provides

@Module
class DataModule(mContext: Context) {
    private val mContext: Context

    @Provides
    @Singleton
    fun provideDatabaseHelper(): DatabaseHelper {
        val movieDatabase: MovieDatabase? = MovieDatabase.getInstance(mContext)
        return DatabaseHelper(movieDatabase)
    }

    init {
        this.mContext = mContext
    }
}*/
