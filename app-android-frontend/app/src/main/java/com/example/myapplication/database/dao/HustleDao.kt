package com.example.myapplication.database.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.myapplication.database.model.Hustle

/**
 * Interface to Hustles in Room Database
 */
@Dao
interface HustleDao {
    /**
     * Insert a hustle
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(hustle: Hustle)

    /**
     * Update a hustle
     */
    @Update
    fun update(hustle: Hustle)

    /**
     * Get a hustle given it's id
     */
    @Query("SELECT * from hustle_table WHERE _id = :id")
    fun get(id: String): Hustle?

    /**
     * Get all hustles
     */
    @Query("SELECT * from hustle_table")
    fun getAll(): LiveData<List<Hustle>>

    /**
     * Insert a list Hustles
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(hustles: List<Hustle>)

    /**
     * Delete all hustles for refresh
     */
    @Query("DELETE from hustle_table")
    fun deleteAll()
}