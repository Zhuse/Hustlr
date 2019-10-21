package com.example.myapplication.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hustle_table")
data class Hustle(
    @PrimaryKey(autoGenerate = true)
    var hustleId: Long = 0L,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "provider")
    val provider: Hustlr,

    @ColumnInfo(name = "date_posted")
    val datePosted: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "price")
    val price: Int,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "categories")
    val categories: List<String>,

    @ColumnInfo(name = "location")
    val location: String
)