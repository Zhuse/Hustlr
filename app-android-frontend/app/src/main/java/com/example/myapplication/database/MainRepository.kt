package com.example.myapplication.database

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Repository for fetching data from the network and storing them in the database,
 * as well as being the access point for the rest of the rest of the application's
 * persistent data.
 */
class MainRepository private  constructor(private val database: MainDatabase) {

    /**
     * Refresh the hustles stored in the offline database
     */
    suspend fun refreshHustles() {
        withContext(Dispatchers.IO) {
            // Fetch data from the REST Api

            // Store data into the database
        }
    }

    /**
     * Refresh the hustlrs stored in the offline database
     */
    suspend fun refreshHustlrs() {
        withContext(Dispatchers.IO) {
            // Fetch data from the REST Api

            // Store the data into the database
        }
    }

    companion object {
        @Volatile
        private var instance: MainRepository? = null

        fun getInstance(database: MainDatabase) : MainRepository {
            if(instance == null) {
                instance = MainRepository(database)
            }

            return instance!!
        }
    }
}