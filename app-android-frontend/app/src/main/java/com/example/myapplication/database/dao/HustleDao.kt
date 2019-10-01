package com.example.myapplication.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.myapplication.database.model.Hustle

@Dao
interface HustleDao {
    @Insert
    fun insert(hustle: Hustle)

    @Update
    fun update(hustle: Hustle)

    @Query("SELECT * from hustle_table WHERE hustleId = :id")
    fun get(id: Long): Hustle?
}