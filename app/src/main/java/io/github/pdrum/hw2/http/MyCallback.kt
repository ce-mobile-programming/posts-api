package io.github.pdrum.hw2.http

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyCallback<T>(
    private val onSuccess: (T?) -> Unit,
    private val onNetworkFailure: () -> Unit,
    private val onHttpFailure: (Response<T>) -> Unit
) : Callback<T> {
    val tag = "HTTP"


    override fun onFailure(call: Call<T>, t: Throwable) {
        Log.e(tag, t.message, t)
        onNetworkFailure()
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (response.code() in 200..300)
            onSuccess(response.body())
        else {
            Log.e("HTTP", "otp request resulted in status=${response.code()}, body=${response.raw()}")
            onHttpFailure(response)
        }
    }
}