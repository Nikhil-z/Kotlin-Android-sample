package com.example.kotlin.myapplication.listeners

import com.example.kotlin.myapplication.model.repository.local.entity.NotesEntity

interface NoteItemsListener {

    fun onItemClicked(note: NotesEntity)
    fun onItemLongClicked(note: NotesEntity)
    fun onItemDeleted(note: NotesEntity)
}