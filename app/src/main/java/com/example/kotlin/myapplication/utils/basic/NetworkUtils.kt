package com.example.kotlin.myapplication.utils.basic

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.example.kotlin.myapplication.listeners.InternetConnectionListener
import com.example.kotlin.myapplication.listeners.NetworkConnectionInterceptor
import com.example.kotlin.myapplication.App
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit


class NetworkUtils {


    companion object {

        private var mInternetConnectionListener: InternetConnectionListener? = null


        fun setInternetConnectionListener(listener: InternetConnectionListener) {
            mInternetConnectionListener = listener
        }

        fun removeInternetConnectionListener() {
            mInternetConnectionListener = null
        }

        fun isInternetAvailable(): Boolean {
            val connectivityManager =
                App.instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
            val activeNetworkInfo = connectivityManager!!.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }


        fun provideOkHttpClient(): OkHttpClient {
            val okhttpClientBuilder = OkHttpClient.Builder()
            okhttpClientBuilder.connectTimeout(30, TimeUnit.SECONDS)
            okhttpClientBuilder.readTimeout(30, TimeUnit.SECONDS)
            okhttpClientBuilder.writeTimeout(30, TimeUnit.SECONDS)

            okhttpClientBuilder.addInterceptor(object : NetworkConnectionInterceptor() {
                override val isInternetAvailable: Boolean
                    get() = isInternetAvailable()

                override fun onInternetUnavailable() {
                    if (mInternetConnectionListener != null) {
                        mInternetConnectionListener!!.onInternetUnavailable()
                    } else Log.e("NetworkUtils", "Remote listener not initiated!")

                }
            })

            return okhttpClientBuilder.build()
        }
    }


}