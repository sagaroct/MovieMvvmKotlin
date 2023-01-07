
package com.air.movieapp.data.network

import com.air.movieapp.data.model.Results
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by sagar on 20/8/16.
 */
interface MovieApiService {

    @GET("movie/{category}")
    suspend fun getMovies(@Path("category") category: String?, @Query("api_key") apiKey: String?, @Query("page") page: Int): Results

}