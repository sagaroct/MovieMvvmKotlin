package com.air.movieapp.data

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.air.movieapp.model.Movie

/**
 * Created by sagar on 22/9/17.
 */
@Database(entities = [Movie::class], version = 1, )
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao?

    companion object {
        private val TAG: String? = MovieDatabase::class.java.getSimpleName()
        private const val  DATABASE_NAME = "movies"

        // For Singleton instantiation
        private val LOCK =  Any()
        private var sInstance: MovieDatabase? = null
        fun getInstance(context: Context): MovieDatabase? {
            Log.d(TAG, "Getting the database")
            if (sInstance == null) {
                synchronized(LOCK) {
                    sInstance = Room.databaseBuilder(context.getApplicationContext(),
                            MovieDatabase::class.java, DATABASE_NAME).allowMainThreadQueries().build()
                    Log.d(TAG, "Made new database")
                }
            }
            return sInstance
        }
    }
}