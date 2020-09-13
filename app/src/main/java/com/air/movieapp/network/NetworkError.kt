package com.air.movieapp.network


class NetworkError(val error: Throwable?) : Throwable(error) {
    /* public boolean isAuthFailure() {
        return error instanceof HttpException &&
                ((HttpException) error).code() == HTTP_UNAUTHORIZED;
    }

    public boolean isResponseNull() {
        return error instanceof HttpException && ((HttpException) error).response() == null;
    }

    public String getAppErrorMessage() {
        if (this.error instanceof IOException) return NETWORK_ERROR_MESSAGE;
        if (!(this.error instanceof HttpException)) return DEFAULT_ERROR_MESSAGE;
        retrofit2.Response<?> response = ((HttpException) this.error).response();
        if (response != null) {
            String status = getJsonStringFromResponse(response);
            if (!TextUtils.isEmpty(status)) return status;

            Map<String, List<String>> headers = response.headers().toMultimap();
            if (headers.containsKey(ERROR_MESSAGE_HEADER))
                return headers.get(ERROR_MESSAGE_HEADER).get(0);
        }

        return DEFAULT_ERROR_MESSAGE;
    }
*/
    /*  protected String getJsonStringFromResponse(final retrofit2.Response<?> response) {
        try {
            String jsonString = response.errorBody().string();
            Response errorResponse = new Gson().fromJson(jsonString, Response.class);
            return errorResponse.status;
        } catch (Exception e) {
            return null;
        }
    }*/
    override val message: String?
        get() = error?.message

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        val that = o as NetworkError?
        return error?.equals(that!!.error) ?: (that!!.error == null)
    }

   /* override fun equals(o: Object?): Boolean {
        if (this === o) return true
        if (o == null || getClass() !== o.getClass()) return false
        val that = o as NetworkError?
        return error?.equals(that!!.error) ?: (that!!.error == null)
    }*/

    override fun hashCode(): Int {
        return error?.hashCode() ?: 0
    }

    companion object {
        val DEFAULT_ERROR_MESSAGE: String? = "Something went wrong! Please try again."
        val NETWORK_ERROR_MESSAGE: String? = "No Internet Connection!"
        private val ERROR_MESSAGE_HEADER: String? = "Error-Message"
    }

}