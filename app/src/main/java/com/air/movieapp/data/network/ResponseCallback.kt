package com.air.movieapp.data.network

import com.air.movieapp.data.network.NetworkError.Companion.DEFAULT_ERROR_MESSAGE
import java.net.SocketTimeoutException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class ResponseCallback<T> : Callback<T> {
    abstract fun successFromNetwork(t: T)
    abstract fun successFromDatabase(t: T)
    abstract fun failure(error: NetworkError)
    abstract fun onTimeOut(call: Call<T>)

    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (response.isSuccessful) {
            response.body()?.let { body ->
                successFromNetwork(body)
            } ?: failure(NetworkError(DEFAULT_ERROR_MESSAGE))
        }
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        if (t is SocketTimeoutException) {
            onTimeOut(call)
        } else {
            failure(NetworkError(t))
        }
    }
}