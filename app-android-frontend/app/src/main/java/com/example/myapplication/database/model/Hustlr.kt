package com.example.myapplication.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Local database representation of a Hustlr user.
 */
@Entity(tableName = "hustlr_table")
data class Hustlr(
    @PrimaryKey
    var _id: String = "",

    @ColumnInfo(name = "name")
    var name: String = "",

    @ColumnInfo(name = "hustles_provided")
    var hustlesProvided: List<Hustle> = listOf()
)