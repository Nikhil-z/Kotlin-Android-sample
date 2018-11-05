package com.example.kotlin.myapplication.model.repository.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.example.kotlin.myapplication.model.repository.local.converter.Converter
import com.example.kotlin.myapplication.model.repository.local.dao.MovieDao
import com.example.kotlin.myapplication.model.repository.local.dao.NotesDao
import com.example.kotlin.myapplication.model.repository.local.entity.MovieEntity
import com.example.kotlin.myapplication.model.repository.local.entity.NotesEntity

@Database(entities = [MovieEntity::class, NotesEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDAO(): MovieDao
    abstract fun notesDao(): NotesDao


    companion object {
        @Volatile
        private var sInstance: AppDatabase? = null

        /**
         * Gets the singleton instance of Database.
         *
         * @param context The context.
         * @return The singleton instance of Database.
         */
        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (sInstance == null) {
                sInstance = Room
                    .databaseBuilder(context.applicationContext, AppDatabase::class.java, "kotlin_database")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return sInstance!!
        }
    }

}