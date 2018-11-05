package com.example.kotlin.myapplication.utils

class AppUtils {


    companion object {


        fun validate(array: BooleanArray): Boolean {
            for (b in array) if (!b) return false
            return true
        }


    }
}