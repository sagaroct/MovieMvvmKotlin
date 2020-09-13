package com.air.movieapp.data

import com.air.movieapp.model.Movie

class DatabaseHelper(movieDatabase: MovieDatabase?) {
    private val mMovieDatabase: MovieDatabase? = movieDatabase
  /*  fun getMovies(category: String?): List<Movie?>? {
        return mMovieDatabase!!.movieDao()!!.getMoviesByCategory(category)
    }
*/
    private fun setCategoryToMovies(categoryName: String?, movies: List<Movie?>?) {
        if (movies != null && !movies.isEmpty()) {
            for (movie in movies) {
                movie?.type = (categoryName)
            }
        }
    }

    fun saveMovieList(categoryName: String?, movies: List<Movie?>?) {
        setCategoryToMovies(categoryName, movies)
        mMovieDatabase!!.movieDao()!!.insertAll(movies)
    }

}