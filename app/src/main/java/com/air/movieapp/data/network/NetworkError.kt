package com.air.movieapp.data.network


class NetworkError(private val error: Throwable?) : Throwable(error) {

    private var errorMsg: String? = null

    constructor(errorMsg: String) : this(null){
       this.errorMsg = errorMsg
    }

    override val message: String?
        get() = error?.message?: errorMsg

    companion object {
        const val DEFAULT_ERROR_MESSAGE = "Something went wrong! Please try again."
        const val NETWORK_ERROR_MESSAGE = "No Internet Connection!"
        private const val ERROR_MESSAGE_HEADER = "Error-Message"
    }

}