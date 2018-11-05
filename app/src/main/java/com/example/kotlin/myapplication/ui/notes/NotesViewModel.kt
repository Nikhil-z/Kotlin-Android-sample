package com.example.kotlin.myapplication.ui.notes

import android.arch.lifecycle.LiveData
import com.example.kotlin.myapplication.model.repository.DataRepository
import com.example.kotlin.myapplication.model.repository.content.NotesRepository
import com.example.kotlin.myapplication.model.repository.local.entity.NotesEntity
import com.example.kotlin.myapplication.utils.base.BaseAndroidViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel : BaseAndroidViewModel() {


    private val repository: NotesRepository
    val allNotes: LiveData<List<NotesEntity>>

    init {
        val notesDao = DataRepository.database.notesDao()
        repository = DataRepository.local().notesRepo(notesDao)
        allNotes = repository.allNotes
    }

    fun insert(notesEntity: NotesEntity) {
        getCoroutineScope().launch(Dispatchers.IO) {
            repository.insert(notesEntity)
        }
    }

    fun delete(notesEntity: NotesEntity) {
        getCoroutineScope().launch(Dispatchers.IO) {
            repository.delete(notesEntity)
        }
    }

}