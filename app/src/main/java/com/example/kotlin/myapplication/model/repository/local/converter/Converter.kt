package com.example.kotlin.myapplication.model.repository.local.converter

import android.arch.persistence.room.TypeConverter
import com.example.kotlin.myapplication.model.data.Rating
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*


class Converter {

    @TypeConverter
    fun fromString(value: String): List<Rating> {
        val listType = object : TypeToken<List<Rating>>() {
        }.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: List<Rating>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return if (value == null) null else Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }


}