package com.example.kotlin.myapplication.ui.movie

import android.arch.lifecycle.LiveData
import com.example.kotlin.myapplication.model.data.Movie
import com.example.kotlin.myapplication.model.repository.DataRepository
import com.example.kotlin.myapplication.model.repository.content.MovieRepository
import com.example.kotlin.myapplication.model.repository.local.entity.MovieEntity
import com.example.kotlin.myapplication.utils.base.BaseAndroidViewModel

class MoviesViewModel : BaseAndroidViewModel() {

    private var apiMovies: LiveData<ArrayList<Movie>>? = null
    private var movies: LiveData<ArrayList<MovieEntity>>? = null

    private val repository: MovieRepository
    val allMovies: LiveData<List<MovieEntity>>

    init {
        val movieDao = DataRepository.database.movieDAO()
        repository = DataRepository.local().movieRepo(movieDao)
        allMovies = repository.allMovies
    }


    fun getMovieList(token: String): LiveData<ArrayList<Movie>> {
        apiMovies = DataRepository.instance.remote().getMovieList(token)
        return apiMovies as LiveData<ArrayList<Movie>>
    }

     fun insert(movies: ArrayList<Movie>) {
        repository.insertMovies(movies)
    }


}