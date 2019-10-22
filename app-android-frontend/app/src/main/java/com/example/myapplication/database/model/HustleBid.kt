package com.example.myapplication.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hustle_bid_table")
data class HustleBid(
    @PrimaryKey
    var _id: String = "",

    @ColumnInfo
    val hustleId: String,

    @ColumnInfo
    val bidPrice: Int,

    @ColumnInfo
    val bidderId: String,

    @ColumnInfo
    val bidAccepted: Boolean = false,

    @ColumnInfo
    val datePosted: Long = System.currentTimeMillis()
)