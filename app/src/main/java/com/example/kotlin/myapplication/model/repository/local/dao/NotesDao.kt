package com.example.kotlin.myapplication.model.repository.local.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.example.kotlin.myapplication.model.repository.local.entity.NotesEntity


@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(notes: ArrayList<NotesEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(notesEntity: NotesEntity)

    @Query("SELECT * from NotesEntity")
    fun getAllLocalMovies(): LiveData<List<NotesEntity>>

    @Delete()
    fun delete(notesEntity: NotesEntity)

    @Query("DELETE FROM NotesEntity")
    fun deleteAll()

    /*@Query("UPDATE NotesEntity SET title=title WHERE id = id")
    fun updateTitle(title: String, id: Long)*/

    @Update()
    fun Update(notesEntity: NotesEntity)

}