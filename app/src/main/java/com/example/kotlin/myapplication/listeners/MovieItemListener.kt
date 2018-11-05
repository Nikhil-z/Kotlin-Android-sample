package com.example.kotlin.myapplication.listeners

import com.example.kotlin.myapplication.model.repository.local.entity.MovieEntity

interface MovieItemListener {

    fun onItemClicked(movie: MovieEntity)
}