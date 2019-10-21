package com.example.myapplication.database

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.myapplication.database.model.Hustle
import com.example.myapplication.database.model.HustleBid
import com.example.myapplication.database.model.Hustlr
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Repository for fetching data from the network and storing them in the database,
 * as well as being the access point for the rest of the rest of the application's
 * persistent data.
 */
class MainRepository private  constructor(private val database: MainDatabase) {
    var hustles: LiveData<List<Hustle>> = database.hustleDao.getAll()
    var hustleBids: LiveData<List<HustleBid>> = database.hustleBidDao.getAll()
    var hustlrs: LiveData<List<Hustlr>> = database.hustlrDao.getAll()

    /**
     * Refresh the hustles stored in the offline database
     */
    suspend fun refreshHustles() {
        withContext(Dispatchers.IO) {
            // Fetch data from the REST Api

            // For now use test hustles
            val newHustles = testHustles()

            // Store data into the database
            database.hustleDao.insertAll(newHustles)
        }
    }

    /**
     * Refresh the hustlrs stored in the offline database
     */
    suspend fun refreshHustlrs() {
        withContext(Dispatchers.IO) {
            // Fetch data from the REST Api

            // Store the data into the local database
        }
    }

    /**
     * Refresh the hustleBids stored in the offline database
     */
    suspend fun refreshHustleBids() {
        withContext(Dispatchers.IO) {
            // Fetch data from the REST Api

            // Store the data into the local database
        }
    }

    /**
     * Post a new hustleBid and store it in the offline database
     */
    suspend fun postHustleBid(bid: HustleBid) {
        withContext(Dispatchers.IO) {
            // Post the bid via REST Api

            // Get the Posted bid (with the correct ID)
            val postedBid = bid // Change this

            // Store it into the local database
            database.hustleBidDao.insert(postedBid)
        }
    }

    /**
     * Get a specified Hustle by ID. This Hustle should exist in the local database
     */
    fun getHustleById(id: Long) : Hustle? {
        return database.hustleDao.get(id)
    }

    fun testHustles() : List<Hustle> {
        val provider = Hustlr(name = "John Wick")
        val list = mutableListOf<Hustle>()

        for(i in 1..10) {
            val hustle = Hustle(i.toLong(), "Help Moving Out", provider, price = 25, description = "I need help moving my stuff out of the house especially after tomorrow night",
                categories = listOf("Small Jobs,", "Heavy Muscle"), location = "1234 Safe St"
            )
            list.add(hustle)
        }

        return list
    }

    suspend fun refreshAll() {
        withContext(Dispatchers.IO) {
            refreshHustleBids()
            refreshHustles()
            refreshHustlrs()
        }
    }

    companion object {
        @Volatile
        private var instance: MainRepository? = null

        private var TAG = this::class.java.canonicalName

        fun getInstance(database: MainDatabase) : MainRepository {
            if(instance == null) {
                instance = MainRepository(database)
            }

            return instance!!
        }
    }
}