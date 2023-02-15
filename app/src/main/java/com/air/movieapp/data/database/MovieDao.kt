package com.air.movieapp.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.air.movieapp.data.model.Movie

/**
 * Created by sagar on 22/9/17.
 */
@Dao
abstract class MovieDao: BaseDao<Movie>() {

    @Query("SELECT * FROM Movie WHERE type = :category")
    abstract suspend fun getMoviesByCategory(category: String): List<Movie>

    @Insert
    abstract suspend fun insertAll(movies: List<Movie>)

}