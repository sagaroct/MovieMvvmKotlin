package com.air.movieapp.data.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.air.movieapp.data.model.Movie

/**
 * Created by sagar on 22/9/17.
 */
@Database(entities = [Movie::class], version = 1, )
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        private const val TAG = "MovieDatabase"
        private const val  DATABASE_NAME = "movies_db"

        // For Singleton instantiation
        private val LOCK =  Any()
        private lateinit var sInstance: MovieDatabase

        fun getInstance(context: Context): MovieDatabase {
            Log.d(TAG, "Getting the database")
            if (this::sInstance.isInitialized.not()) {
                synchronized(LOCK) {
                    sInstance = Room.databaseBuilder(context.applicationContext,
                            MovieDatabase::class.java, DATABASE_NAME
                    ).fallbackToDestructiveMigration().build()
                    Log.d(TAG, "Made new database")
                }
            }
            return sInstance
        }
    }
}