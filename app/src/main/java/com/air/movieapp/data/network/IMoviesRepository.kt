package com.air.movieapp.data.network

import com.air.movieapp.data.model.Movie
import com.air.movieapp.data.model.Results
import kotlinx.coroutines.flow.Flow

/**
 * Created by Sagar Pujari on 24/01/23.
 */
interface IMoviesRepository {

    fun getMoviesFromApi(category: String, page: Int): Flow<List<Movie>>

    suspend fun getMoviesFromDb(category: String): List<Movie>

}