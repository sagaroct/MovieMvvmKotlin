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
                0, "someTitle", "1992-12-10", "soneOverView", 1.00f, Constants.POPULAR
            )
        )
    )

}