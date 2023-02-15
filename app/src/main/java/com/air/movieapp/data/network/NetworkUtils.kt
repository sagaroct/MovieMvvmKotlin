package com.air.movieapp.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class NetworkUtils(private val context: Context) {

    /**
     * Check if the network is connected
     *
     * @return true/false if network is connected or not
     */
    val isNetworkConnected: Boolean
        get() {
            var isConnected = false
            val connectivityManager: ConnectivityManager = context
                    .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo: Array<NetworkInfo?> = connectivityManager.getAllNetworkInfo()
            for (aNetworkInfo in networkInfo) {
                if (aNetworkInfo!!.isConnected) {
                    isConnected = true
                    break
                }
            }
            return isConnected
        }

}