package com.example.myapplication.database.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.myapplication.database.model.Hustlr

/**
 * Interface to Hustlrs in Room Database
 */
@Dao
interface HustlrDao {
    /**
     * Insert a hustlr
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(hustlr: Hustlr)

    /**
     * Insert a list of hustlrs
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(hustlrs: List<Hustlr>)

    /**
     * Update a hustlr
     */
    @Update
    fun update(hustlr: Hustlr)

    /**
     * Get a hustlr by ID
     */
    @Query("SELECT * from hustlr_table WHERE _id = :id")
    fun get(id: String) : Hustlr?

    /**
     * Get all hustlrs
     */
    @Query("SELECT * from hustlr_table")
    fun getAll() : LiveData<List<Hustlr>>
}