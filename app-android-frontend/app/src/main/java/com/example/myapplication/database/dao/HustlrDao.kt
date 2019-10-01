package com.example.myapplication.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.myapplication.database.model.Hustlr

@Dao
interface HustlrDao {
    @Insert
    fun insert(hustlr: Hustlr)

    @Update
    fun update(hustlr: Hustlr)

    @Query("SELECT * from hustlr_table WHERE hustlrId = :id")
    fun get(id: Long) : Hustlr?
}