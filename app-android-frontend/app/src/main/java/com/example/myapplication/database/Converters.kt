package com.example.myapplication.database

import androidx.room.TypeConverter
import com.example.myapplication.database.model.Hustle
import com.example.myapplication.database.model.HustleBid
import com.example.myapplication.database.model.Hustlr
import com.google.gson.Gson

/**
 * This class is used by Room to convert from supported primitives to complex data types
 * and vice versa
 */
class Converters {

    /**
     * Convert from a Hustle to a JSON string
     */
    @TypeConverter
    fun hustleToJson(value: Hustle?) : String {
        return Gson().toJson(value)
    }

    /**
     * Convert from a JSON string to a Hustle
     */
    @TypeConverter
    fun jsonToHustle(value: String) : Hustle? {
        val item = Gson().fromJson(value, Hustle::class.java) as Hustle
        return item
    }

    /**
     * Convert from a list of Hustles to a JSON string
     */
    @TypeConverter
    fun hustleListToJson(value: List<Hustle>?) : String {
        return Gson().toJson(value)
    }

    /**
     * Convert from a JSON string to a list of Hustles
     */
    @TypeConverter
    fun jsonToHustleList(value: String) : List<Hustle>? {
        val objects = Gson().fromJson(value, Array<Hustle>::class.java) as Array<Hustle>
        val list = objects.toList()
        return list
    }

    /**
     * Convert from a Hustlr to a JSON string
     */
    @TypeConverter
    fun hustlrToJson(value: Hustlr?) : String {
        return Gson().toJson(value)
    }

    /**
     * Convert from a JSON string to a Hustlr
     */
    @TypeConverter
    fun jsonToHustlr(value: String) : Hustlr? {
        val item = Gson().fromJson(value, Hustlr::class.java) as Hustlr
        return item
    }

    /**
     * Convert from a list of Hustlrs to a JSON string
     */
    @TypeConverter
    fun hustlrListToJson(value: List<Hustlr>?) : String {
        return Gson().toJson(value)
    }

    /**
     * Convert from a JSON string to a list of Hustlrs
     */
    @TypeConverter
    fun jsonToHustlrList(value: String) : List<Hustlr>? {
        val objects = Gson().fromJson(value, Array<Hustlr>::class.java) as Array<Hustlr>
        val list = objects.toList()
        return list
    }

    /**
     * Convert from a list of Strings representing Categories to a JSON string
     */
    @TypeConverter
    fun categoriesListToJson(value: List<String>?) : String {
        return Gson().toJson(value)
    }

    /**
     * Convert from a JSON string to a list of String representing categories
     */
    @TypeConverter
    fun jsonToCategoriesList(value: String) : List<String>? {
        val objects = Gson().fromJson(value, Array<String>::class.java) as Array<String>
        val list = objects.toList()
        return list
    }

    /**
     * Convert from a list of Hustle Bids to a Json string
     */
    @TypeConverter
    fun hustleBidListToJson(value: List<HustleBid>) : String {
        return Gson().toJson(value)
    }

    /**
     * Convert from a JSON string to a list of HustleBid
     */
    @TypeConverter
    fun jsonToHustleBidList(value: String) : List<HustleBid> {
        val objects = Gson().fromJson(value, Array<HustleBid>::class.java) as Array<HustleBid>
        val list = objects.toList()
        return list
    }
}