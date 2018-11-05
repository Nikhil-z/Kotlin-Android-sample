package com.example.kotlin.myapplication.model.repository

import com.example.kotlin.myapplication.App
import com.example.kotlin.myapplication.model.repository.local.AppDatabase
import com.example.kotlin.myapplication.model.repository.remote.RestApi
import com.example.kotlin.myapplication.utils.basic.NetworkUtils
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class DataRepository private constructor() {

    private val restApi: RestApi
    private val retrofit: Retrofit


    init {

        val gsonBuilder = GsonBuilder()
        gsonBuilder.setLenient()
        val gson = gsonBuilder.create()

        retrofit = Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/")
            .client(NetworkUtils.provideOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        restApi = retrofit.create(RestApi::class.java)

    }

    companion object {
        private var dataRepository: DataRepository? = null

        val instance: DataRepository
            @Synchronized get() {
                if (dataRepository == null) {
                    dataRepository =
                            DataRepository()
                }
                return dataRepository as DataRepository
            }

        /*All local data operation take place from here*/
        fun local(): Local {
            return Local()
        }


        val database = AppDatabase.getInstance(context = App.instance)
    }

    /*All network data operation take place from here*/
    fun remote(): Remote {
        return Remote(restApi)
    }


}


