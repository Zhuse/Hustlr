package com.example.myapplication.database

import androidx.room.TypeConverter
import com.example.myapplication.database.model.Hustle
import com.example.myapplication.database.model.Hustlr
import com.google.gson.Gson

class Converters {

    // Hustle Converters
    @TypeConverter
    fun hustleToJson(value: Hustle?) : String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToHustle(value: String) : Hustle? {
        val item = Gson().fromJson(value, Hustle::class.java) as Hustle
        return item
    }

    @TypeConverter
    fun hustleListToJson(value: List<Hustle>?) : String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToHustleList(value: String) : List<Hustle>? {
        val objects = Gson().fromJson(value, Array<Hustle>::class.java) as Array<Hustle>
        val list = objects.toList()
        return list
    }

    // Hustlr Converters
    @TypeConverter
    fun hustlrToJson(value: Hustlr?) : String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToHustlr(value: String) : Hustlr? {
        val item = Gson().fromJson(value, Hustlr::class.java) as Hustlr
        return item
    }

    @TypeConverter
    fun hustlrListToJson(value: List<Hustlr>?) : String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToHustlrList(value: String) : List<Hustlr>? {
        val objects = Gson().fromJson(value, Array<Hustlr>::class.java) as Array<Hustlr>
        val list = objects.toList()
        return list
    }
}