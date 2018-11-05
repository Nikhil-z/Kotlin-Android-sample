package com.example.kotlin.myapplication.utils.basic

import android.os.Handler
import android.widget.Toast
import com.example.kotlin.myapplication.App
import android.os.Looper


class ToastUtils {

    companion object {

        fun showToast(msg: String) {

            Handler(Looper.getMainLooper()).post {
                Toast.makeText(
                    App.instance, msg,
                    Toast.LENGTH_SHORT
                ).show()
            }

        }

        fun showLongToast(msg: String) {

            Handler(Looper.getMainLooper()).post {
                Toast.makeText(
                    App.instance, msg,
                    Toast.LENGTH_LONG
                ).show()
            }

        }
    }
}