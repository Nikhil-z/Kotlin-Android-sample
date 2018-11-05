package com.example.kotlin.myapplication.model.repository.local.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.example.kotlin.myapplication.model.repository.local.entity.MovieEntity

@Dao
interface MovieDao {

    @Insert(onConflict = REPLACE)
    fun insertAll(movies: ArrayList<MovieEntity>)

    @Insert(onConflict = REPLACE)
    fun insert(movie: MovieEntity)


    @get:Query("SELECT * FROM MovieEntity")
    val all: List<MovieEntity>


    @Query("DELETE from MovieEntity")
    fun deleteAll()


    @Query("SELECT * FROM MovieEntity WHERE title == :name")
    fun getMovieByName(name: String): List<MovieEntity>

    @Query("SELECT * FROM MovieEntity")
    fun getAllMovies(): LiveData<List<MovieEntity>>

}