package com.example.compose.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class TypeConvertersClass {
    @TypeConverter
    fun fromListToStringJson(value: List<String>): String {

        return Gson().toJson(value)
    }

    @TypeConverter
    fun toListfromStringJson(value: String): List<String> {
        return try {
            val listType: Type = object : TypeToken<List<String>?>() {}.type
            Gson().fromJson(value,listType)
        } catch (e: Exception) {
            listOf()
        }
    }
}