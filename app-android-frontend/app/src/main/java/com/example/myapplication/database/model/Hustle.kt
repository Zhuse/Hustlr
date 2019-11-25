package com.example.myapplication.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

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

//    @ColumnInfo(name = "date_posted")
//    val datePosted: String,

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
    val status: String,

    @ColumnInfo(name = "hustlr_id")
    val hustlrId: String?,

    @ColumnInfo(name = "bids")
    val bids: List<HustleBid>?
)