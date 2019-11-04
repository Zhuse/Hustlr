package com.example.myapplication.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Status of a Hustle.
 */
enum class HustleStatus {
    completed, in_prog, cancelled, posted
}

/**
 * Local database representation of a hustle.
 */
@Entity(tableName = "hustle_table")
data class Hustle(
    @PrimaryKey
    var _id: String = "",

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "provider")
    val providerId: String,

    @ColumnInfo(name = "date_posted")
    val datePosted: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "price")
    val price: Int,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "category")
//    val categories: List<String>,
    val category: String,

    @ColumnInfo(name = "location")
    val location: String,

    @ColumnInfo(name = "status")
    val status: String
)