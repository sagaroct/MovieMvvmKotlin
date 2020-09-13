package com.air.movieapp.network

import java.net.SocketTimeoutException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response.success
import kotlin.jvm.Throws

/**
 * Created by shreesha on 30/12/16.
 */
abstract class ResponseCallback<T> : Callback<T?> {
    abstract fun successFromNetwork(t: T?)
    abstract fun successFromDatabase(t: T?)
    abstract fun failure(call: Call<T?>?, error: NetworkError?)
    abstract fun onTimeOut(call: Call<T?>?)

    override fun onResponse(call: Call<T?>?, response: retrofit2.Response<T?>?) {
        if (response != null && response.isSuccessful()) {
            successFromNetwork(response.body())
        }
    }

    override fun onFailure(call: Call<T?>?, t: Throwable?) {
        if (t is SocketTimeoutException) {
            onTimeOut(call)
        } else {
            failure(call, NetworkError(t))
        }
    }
}