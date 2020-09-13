/*
 *
 *  * Copyright © 2016, Robosoft Technologies Pvt. Ltd
 *  * Written under contract by Robosoft Technologies Pvt. Ltd.
 *
 */
package com.air.movieapp.network

import com.air.movieapp.model.Results
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import kotlin.jvm.Throws

/**
 * Created by sagar on 20/8/16.
 */
interface MovieApiService {
    @GET("movie/{category}")
    fun getMovies(@Path("category") category: String?, @Query("api_key") apiKey: String?, @Query("page") page: Int): Call<Results?>?
}