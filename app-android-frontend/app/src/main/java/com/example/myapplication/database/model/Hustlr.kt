package com.example.myapplication.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hustlr_table")
data class Hustlr(
    @PrimaryKey(autoGenerate = true)
    var hustlrId: Long = 0L,

    @ColumnInfo(name = "name")
    var name: String = "",

    @ColumnInfo(name = "hustles_provided")
    var hustlesProvided: List<Hustle> = listOf()
)