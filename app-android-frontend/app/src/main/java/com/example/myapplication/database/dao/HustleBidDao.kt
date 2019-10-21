package com.example.myapplication.database.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.myapplication.database.model.HustleBid

@Dao
interface HustleBidDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(hustleBid: HustleBid)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(bids: List<HustleBid>)

    @Update
    fun update(hustleBid: HustleBid)

    @Query("SELECT * FROM hustle_bid_table WHERE hustleBidId = :id")
    fun get(id: Long) : HustleBid?

    @Query("SELECT * FROM hustle_bid_table")
    fun getAll() : LiveData<List<HustleBid>>

    @Query("SELECT * FROM hustle_bid_table WHERE providerId = :id")
    fun getHustleBidsByProvider(id: Long) : LiveData<List<HustleBid>>

    @Query("SELECT * FROM hustle_bid_table WHERE bidderId = :id")
    fun getHustleBidsByBidder(id: Long) : LiveData<List<HustleBid>>

    @Query("SELECT * FROM hustle_bid_table WHERE hustleId = :id")
    fun getHustleBidsByHustleId(id: Long) : LiveData<List<HustleBid>>
}