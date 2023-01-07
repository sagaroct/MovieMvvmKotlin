package com.air.movieapp.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class NetworkUtils(context: Context?) {
    private val mContext: Context?

    /**
     * Check if the network is connected
     *
     * @return true/false if network is connected or not
     */
    val isNetworkConnected: Boolean
        get() {
            var isConnected = false
            if (mContext == null) return false
            val connectivityManager: ConnectivityManager = mContext
                    .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (connectivityManager != null) {
                val networkInfo: Array<NetworkInfo?> = connectivityManager.getAllNetworkInfo()
                if (networkInfo != null) {
                    for (aNetworkInfo in networkInfo) {
                        if (aNetworkInfo!!.isConnected) {
                            isConnected = true
                            break
                        }
                    }
                }
            }
            return isConnected
        }

    fun setCacheType(cacheType: CacheType?): CacheType? {
        return if (isNetworkConnected) {
            cacheType
        } else {
            CacheType.CACHE
        }
    }

    companion object {
        val TOP_RATED: String? = "top_rated"
        val POPULAR: String? = "popular"
        val UPCOMING: String? = "upcoming"
    }

    init {
        mContext = context
    }
}