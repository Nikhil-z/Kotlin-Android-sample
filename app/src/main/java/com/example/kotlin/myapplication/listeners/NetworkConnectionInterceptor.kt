package com.example.kotlin.myapplication.listeners

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import java.io.IOException


abstract class NetworkConnectionInterceptor : Interceptor {

    abstract val isInternetAvailable: Boolean

    abstract fun onInternetUnavailable()

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        if (!isInternetAvailable) {
            onInternetUnavailable()
        }
        val body = response.body()
        val bodyString = body!!.string()
        val contentType = body.contentType()
        Log.d("Response", bodyString)
        return response.newBuilder().body(ResponseBody.create(contentType, bodyString)).build()

    }

}