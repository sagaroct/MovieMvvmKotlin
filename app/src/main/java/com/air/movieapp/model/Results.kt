package com.air.movieapp.model

import com.google.gson.annotations.SerializedName

/**
 * Created by sagar on 20/8/16.
 */
class Results {
    @SerializedName("page")
    val page = 0

    @SerializedName("results")
    var movies: List<Movie>? = null

}