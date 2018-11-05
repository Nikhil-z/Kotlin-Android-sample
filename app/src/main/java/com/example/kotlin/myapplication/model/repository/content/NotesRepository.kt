package com.example.kotlin.myapplication.model.repository.content

import android.arch.lifecycle.LiveData
import androidx.annotation.WorkerThread
import com.example.kotlin.myapplication.model.repository.local.dao.NotesDao
import com.example.kotlin.myapplication.model.repository.local.entity.NotesEntity

class NotesRepository(private var notesDao: NotesDao) {

    val allNotes: LiveData<List<NotesEntity>> = notesDao.getAllLocalMovies()

    @WorkerThread
    suspend fun insert(movieEntity: NotesEntity) {
        notesDao.insert(movieEntity)
    }

    @WorkerThread
    suspend fun delete(movieEntity: NotesEntity) {
        notesDao.delete(movieEntity)
    }
}