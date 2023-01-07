package com.air.movieapp.common

/**
 * Created by sagar on 20/8/16.
 */
object Constants {


    const val TOP_RATED: String = "top_rated"
    private const val UPCOMING: String = "upcoming"
    private const val POPULAR: String = "popular"
    val CATEGORIES = arrayOf(TOP_RATED, UPCOMING, POPULAR)
    const val PAGE_SIZE = 20
    const val SETTINGS_REQUEST_CODE = 10

    enum class SortType {
        TITLE
    }
    
}