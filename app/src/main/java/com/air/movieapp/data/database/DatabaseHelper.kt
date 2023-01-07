/*
package com.air.movieapp.data.database

import com.air.movieapp.data.model.Movie

internal class DatabaseHelper(private val movieDatabase: MovieDatabase) {

    suspend fun getMovies(category: String): List<Movie> {
        return movieDatabase.movieDao().getMoviesByCategory(category)
    }

    suspend fun saveMovieList(categoryName: String, movies: List<Movie>) {
        setCategoryToMovies(categoryName, movies)
        movieDatabase.movieDao().insertAll(movies)
    }

    private fun setCategoryToMovies(categoryName: String?, movies: List<Movie>) {
            for (movie in movies) {
                movie.type = categoryName
            }
    }



}*/
