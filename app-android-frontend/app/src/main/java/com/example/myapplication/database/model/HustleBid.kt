package com.example.myapplication.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

/**
 * Local database representation of a bid for a hustle.
 */
data class HustleBid(
    var userId: String = "",
    val description: String,
    val bidCost: Int,
    val timestamp: Long = System.currentTimeMillis()
)