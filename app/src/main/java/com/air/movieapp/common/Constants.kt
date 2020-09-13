/*
 *
 *  * Copyright © 2016, Robosoft Technologies Pvt. Ltd
 *  * Written under contract by Robosoft Technologies Pvt. Ltd.
 *
 */
package com.air.movieapp.common

import kotlin.jvm.Throws

/**
 * Created by sagar on 20/8/16.
 */
object Constants {
    val TAB: String? = "tab"
    val TOP_RATED: String? = "top_rated"
    val UPCOMING: String? = "upcoming"
    val POPULAR: String? = "popular"
    const val PAGE_SIZE = 20
    const val SETTINGS_REQUEST_CODE = 10

    enum class SortType {
        TITLE
    }
}