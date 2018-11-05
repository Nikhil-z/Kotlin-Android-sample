package com.example.kotlin.myapplication.utils.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.kotlin.myapplication.listeners.InternetConnectionListener
import com.example.kotlin.myapplication.utils.basic.NetworkUtils
import org.jetbrains.anko.AnkoLogger

open class BaseAppCompactActivity : AppCompatActivity(), InternetConnectionListener, AnkoLogger {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NetworkUtils.setInternetConnectionListener(this)
    }

    override fun onPause() {
        super.onPause()
        NetworkUtils.removeInternetConnectionListener()
    }

    override fun onResume() {
        super.onResume()
        NetworkUtils.setInternetConnectionListener(this)


    }

    override fun onDestroy() {
        super.onDestroy()
        NetworkUtils.removeInternetConnectionListener()
    }


    override fun onInternetUnavailable() {
        error("---- No active internet connection! ----")
    }


}