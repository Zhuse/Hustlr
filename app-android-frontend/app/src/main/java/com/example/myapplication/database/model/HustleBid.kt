package com.example.myapplication.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hustle_bid_table")
data class HustleBid(
    @PrimaryKey(autoGenerate = true)
    var hustleBidId: Long = 0L,

    @ColumnInfo
    val hustleId: Long,

    @ColumnInfo
    val bidPrice: Int,

    @ColumnInfo
    val bidderId: Long,

    @ColumnInfo
    val providerId: Long,

    @ColumnInfo
    val bidAccepted: Boolean = false,

    @ColumnInfo
    val datePosted: Long = System.currentTimeMillis()
)