package com.example.myapplication.database.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.myapplication.database.model.Hustlr

@Dao
interface HustlrDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(hustlr: Hustlr)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(hustlrs: List<Hustlr>)

    @Update
    fun update(hustlr: Hustlr)

    @Query("SELECT * from hustlr_table WHERE hustlrId = :id")
    fun get(id: Long) : Hustlr?

    @Query("SELECT * from hustlr_table")
    fun getAll() : LiveData<List<Hustlr>>
}