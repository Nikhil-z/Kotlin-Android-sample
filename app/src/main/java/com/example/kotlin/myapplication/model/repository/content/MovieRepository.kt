package com.example.kotlin.myapplication.model.repository.content

import android.arch.lifecycle.LiveData
import androidx.annotation.WorkerThread
import com.example.kotlin.myapplication.model.data.Movie
import com.example.kotlin.myapplication.model.repository.local.dao.MovieDao
import com.example.kotlin.myapplication.model.repository.local.entity.MovieEntity
import org.jetbrains.anko.doAsync

class MovieRepository(private var movieDao: MovieDao) {

    val allMovies: LiveData<List<MovieEntity>> = movieDao.getAllMovies()

    @WorkerThread
    suspend fun insert(movieEntity: MovieEntity) {
        movieDao.insert(movieEntity)
    }

    fun insertMovies(movie: ArrayList<Movie>) {

        doAsync {

            val movies: MutableList<MovieEntity> = mutableListOf()

            for (index in movie.indices) {

                val singleMovie = movie[index]

                val client = MovieEntity(
                    singleMovie.title!!,
                    singleMovie.year!!,
                    singleMovie.rated!!,
                    singleMovie.released!!,
                    singleMovie.runtime!!,
                    singleMovie.genre!!,
                    singleMovie.director!!,
                    singleMovie.writer!!,
                    singleMovie.actors!!,
                    singleMovie.plot!!,
                    singleMovie.language!!,
                    singleMovie.country!!,
                    singleMovie.awards!!,
                    singleMovie.poster!!,
                    singleMovie.ratings!!,
                    singleMovie.metascore!!,
                    singleMovie.imdbRating!!,
                    singleMovie.imdbVotes!!,
                    singleMovie.imdbID!!,
                    singleMovie.type!!,
                    singleMovie.dvd!!,
                    singleMovie.boxOffice!!,
                    singleMovie.production!!,
                    singleMovie.website!!,
                    singleMovie.response!!

                )
                movies.add(index, client)
            }

            if (movies.size > 0) {
                movieDao.insertAll(movies = movies as ArrayList<MovieEntity>)
            }

        }
    }
}