package com.example.myapplication.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.database.model.Hustle
import com.example.myapplication.database.model.Hustlr
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Repository for fetching data from the network and storing them in the database,
 * as well as being the access point for the rest of the rest of the application's
 * persistent data.
 */
class MainRepository private  constructor(private val database: MainDatabase) {
    private var hustles: LiveData<List<Hustle>>? = null

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

    fun getAllHustles() : LiveData<List<Hustle>> {
        if(hustles == null) {
            //TODO: Change this
            return testHusltes()
        } else {
            return MutableLiveData<List<Hustle>>()
        }
    }

    fun testHusltes() : LiveData<List<Hustle>> {
        val provider = Hustlr(name = "John Wick")
        val list = mutableListOf<Hustle>()

        for(i in 1..10) {
            val hustle = Hustle(i.toLong(), "Help Moving Out", provider, price = 25.0, description = "I need help moving my stuff out of the house especially after tomorrow night")
            list.add(hustle)
        }

        val result = MutableLiveData<List<Hustle>>()
        result.postValue(list)

        return result
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