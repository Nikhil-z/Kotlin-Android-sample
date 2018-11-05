package com.example.kotlin.myapplication.model.repository.remote

import com.example.kotlin.myapplication.model.data.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface RestApi {

    @GET("Nikhil-z/demo/{auth}/db.json")
    fun getMovieList(@Path("auth") auth: String): Call<ArrayList<Movie>>

}