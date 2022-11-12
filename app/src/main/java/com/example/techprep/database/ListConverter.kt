package com.example.techprep.database

import android.R.attr.data
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*


class ListConverter {
    @TypeConverter
    fun stringToObject(value: String): List<QuestionJson> {
        val gson = Gson()
        val type = object : TypeToken<List<QuestionJson>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun objectToString(objectList: List<QuestionJson>): String {
        val gson = Gson()
        val type = object : TypeToken<List<QuestionJson>>() {}.type
        return gson.toJson(objectList, type)
    }
}