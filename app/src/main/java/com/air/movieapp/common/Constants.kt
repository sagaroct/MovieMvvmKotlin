package com.air.movieapp.common

/**
 * Created by sagar on 20/8/16.
 */
object Constants {


    object RestConstants {
        const val BASE_URL: String = "https://api.themoviedb.org/3/"
        const val AP_KEY: String = "4c989ba3813652e9f29d4dfd44bd34ad"
    }

    const val TOP_RATED: String = "top_rated"
    private const val UPCOMING: String = "upcoming"
    const val POPULAR: String = "popular"
    val CATEGORIES = arrayOf(TOP_RATED, UPCOMING, POPULAR)

    enum class SortType {
        TITLE
    }
    
}