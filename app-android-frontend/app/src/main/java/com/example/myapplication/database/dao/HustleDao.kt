package com.example.myapplication.database.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.myapplication.database.model.Hustle

@Dao
interface HustleDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(hustle: Hustle)

    @Update
    fun update(hustle: Hustle)

    @Query("SELECT * from hustle_table WHERE hustleId = :id")
    fun get(id: String): Hustle?

    @Query("SELECT * from hustle_table")
    fun getAll(): LiveData<List<Hustle>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(hustles: List<Hustle>)
}