package com.example.kotlin.myapplication.model.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.kotlin.myapplication.model.data.Movie
import com.example.kotlin.myapplication.model.repository.remote.RestApi
import retrofit2.Callback
import retrofit2.Response

class Remote(private var restApi: RestApi) {

    fun getMovieList(token: String): LiveData<ArrayList<Movie>> {

        val data = MutableLiveData<ArrayList<Movie>>()
        restApi.getMovieList(token).enqueue(object : Callback<ArrayList<Movie>> {

            override fun onResponse(call: retrofit2.Call<ArrayList<Movie>>, response: Response<ArrayList<Movie>>) {
                if (response.isSuccessful) {
                    data.value = response.body()
                }
            }

            override fun onFailure(call: retrofit2.Call<ArrayList<Movie>>, t: Throwable) {

            }
        })

        return data
    }
}