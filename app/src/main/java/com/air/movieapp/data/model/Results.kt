package com.air.movieapp.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by sagar on 20/8/16.
 */
data class Results (
    @SerializedName("page")
    private val page: Int = 0,

    @SerializedName("results")
    var movies: List<Movie>

)