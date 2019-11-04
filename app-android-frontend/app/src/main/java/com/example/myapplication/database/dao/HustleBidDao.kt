package com.example.myapplication.database.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.myapplication.database.model.HustleBid

/**
 * Interface to HustleBids in the Room Database
 */
@Dao
interface HustleBidDao {
    /**
     * Insert a hustle bid
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(hustleBid: HustleBid)

    /**
     * Insert a list of hustle bids
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(bids: List<HustleBid>)

    /**
     * Update a hustle bid
     */
    @Update
    fun update(hustleBid: HustleBid)

    /**
     * Get a hustle bid with the given ID
     */
    @Query("SELECT * FROM hustle_bid_table WHERE _id = :id")
    fun get(id: String) : HustleBid?

    /**
     * Get all hustle bids
     */
    @Query("SELECT * FROM hustle_bid_table")
    fun getAll() : LiveData<List<HustleBid>>

    /**
     * Get all hustle bids posted by a given bidder id
     */
    @Query("SELECT * FROM hustle_bid_table WHERE bidderId = :id")
    fun getHustleBidsByBidder(id: String) : LiveData<List<HustleBid>>

    /**
     * Get all hustle bids on a given hustle id
     */
    @Query("SELECT * FROM hustle_bid_table WHERE hustleId = :id")
    fun getHustleBidsByHustleId(id: String) : LiveData<List<HustleBid>>
}