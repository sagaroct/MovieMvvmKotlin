package com.air.movieapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import com.air.movieapp.model.Movie

/**
 * Created by sagar on 22/9/17.
 */
@Dao
interface MovieDao {

   /* @Query("SELECT * FROM Movie WHERE type = :category")
    fun getMoviesByCategory(category: String?): List<Movie?>?*/

    @Insert
    fun insertAll(movies: List<Movie?>?)

    @Delete
    fun delete(movie: Movie?)
}