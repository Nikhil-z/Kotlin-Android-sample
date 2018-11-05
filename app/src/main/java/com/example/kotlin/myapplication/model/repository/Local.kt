package com.example.kotlin.myapplication.model.repository

import com.example.kotlin.myapplication.model.repository.content.MovieRepository
import com.example.kotlin.myapplication.model.repository.content.NotesRepository
import com.example.kotlin.myapplication.model.repository.local.dao.MovieDao
import com.example.kotlin.myapplication.model.repository.local.dao.NotesDao

class Local {


    fun movieRepo(movieDao: MovieDao): MovieRepository {
        return MovieRepository(movieDao)
    }

    fun notesRepo(notesDao: NotesDao): NotesRepository {
        return NotesRepository(notesDao)
    }
}