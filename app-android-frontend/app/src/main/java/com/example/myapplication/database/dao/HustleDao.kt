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
    @Insert(onConflict = OnConflictStrategy.REPLACE)
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
     * Get all biddable hustles
     */
    @Query("SELECT * FROM hustle_table WHERE provider != :myId")
    fun getAllBiddableHustles(myId: String) : LiveData<List<Hustle>>

    /**
     * Get all hustles we've posted
     */
    @Query("SELECT * FROM hustle_table WHERE provider == :myId")
    fun getHustlesWePosted(myId: String) : LiveData<List<Hustle>>

    /**
     * Delete all biddable hustles
     */
    @Query("DELETE FROM hustle_table WHERE provider != :myId")
    fun deleteAllBiddableHustles(myId: String)

//    /**
//     * Get all hustles for which we've submitted a bid
//     */
//    @Query("SELECT * from hustle_table WHERE")
//
//    /**
//     * Get all hustles for which we've received a bid
//     */

    /**
     * Insert a list Hustles
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(hustles: List<Hustle>)

    /**
     * Delete all hustles for refresh
     */
    @Query("DELETE from hustle_table")
    fun deleteAll()
}