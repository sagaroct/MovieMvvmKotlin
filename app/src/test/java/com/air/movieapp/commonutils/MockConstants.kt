package com.air.movieapp.commonutils

import com.air.movieapp.common.Constants
import com.air.movieapp.data.model.Movie
import com.air.movieapp.data.model.Results

/**
 * Created by Sagar Pujari on 12/02/23.
 */
object MockConstants {

    val MOCK_RESULTS = Results(
        0, listOf(
            Movie(
                1, "someTitle", "1992-12-10", "soneOverView", 1.00f, Constants.POPULAR
            ),
            Movie(
                2, "Tiger", "2023-15-02", "Overview 2", 2.00f, Constants.POPULAR
            )
        )
    )

}