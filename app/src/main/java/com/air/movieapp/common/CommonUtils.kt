/*
 *
 *  * Copyright © 2016, Robosoft Technologies Pvt. Ltd
 *  * Written under contract by Robosoft Technologies Pvt. Ltd.
 *
 */
package com.air.movieapp.common

import android.os.Bundle

/**
 * Created by sagar on 20/8/16.
 */
object CommonUtils {
    fun getBundleWithValue(pos: Int): Bundle? {
        val bundle = Bundle()
        when (pos) {
            0 -> bundle.putSerializable(Constants.TAB, Constants.TOP_RATED)
            1 -> bundle.putSerializable(Constants.TAB, Constants.UPCOMING)
            2 -> bundle.putSerializable(Constants.TAB, Constants.POPULAR)
        }
        return bundle
    }

}