package com.example.myapplication.database

import android.accounts.AccountManager
import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.myapplication.HustleCategory
import com.example.myapplication.auth.api.UserApi
import com.example.myapplication.database.api.HustleApi
import com.example.myapplication.database.api.HustleModel
import com.example.myapplication.database.model.Hustle
import com.example.myapplication.database.model.HustleBid
import com.example.myapplication.database.model.HustleStatus
import com.example.myapplication.database.model.Hustlr
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Repository for fetching data from the network and storing them in the database,
 * as well as being the access point for the rest of the rest of the application's
 * persistent data.
 */
class MainRepository private  constructor(private val database: MainDatabase, private val application: Application) {
    var hustles: LiveData<List<Hustle>> = database.hustleDao.getAll()
    var hustlrs: LiveData<List<Hustlr>> = database.hustlrDao.getAll()

    private val accountManager: AccountManager by lazy { AccountManager.get(application) }

//    var myHustlrId: Long = 1 // TODO: Change this
    var myHustlrId: String = accountManager.getUserData(accountManager.accounts[0], "userId")

    // Networking Stuff
    private var postHustleDisposable: Disposable? = null
    private var getHustlesDisposable: Disposable? = null
    private val hustleApi: HustleApi by lazy { HustleApi.create() }

    /**
     * Refresh the hustles stored in the offline database
     */
    suspend fun refreshHustles() {
        withContext(Dispatchers.IO) {
            // Fetch data from the REST Api
            val response = hustleApi
                .getHustlesByUserMatched(myHustlrId).execute()

            if(response.isSuccessful) {
                val newHustles = response.body()
                database.hustleDao.deleteAll()
                database.hustleDao.insertAll(newHustles!!.properties.hustles)
            } else if(!response.isSuccessful) {
                Log.i(TAG, "Get Hustles failed")
                Log.w(TAG, response.toString())
            }
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
    suspend fun postHustleBid(description: String, bidCost: Int, hustleId: String) {
        withContext(Dispatchers.IO) {
            // Post the bid via REST Api
            val hustleBidBody = HustleModel.HustleBidRequest(HustleModel.HustleBidRequestProperties(description, bidCost))
            val response = hustleApi.postHustleBid(myHustlrId, hustleId, hustleBidBody).execute()

            if(response.isSuccessful) {
                var updatedHustle = response.body()!!
                database.hustleDao.update(updatedHustle)
            } else {
                Log.w(TAG, "Post HustleBid failed")
            }
        }
    }

    /**
     * Post a new Hustle and store it in the offline database
     */
    suspend fun postHustle(hustle: Hustle) {
        withContext(Dispatchers.IO) {
            // Post the hustle via REST Api
            val hustleBody: HustleModel.HustleRequestModel = HustleModel.HustleRequestModel(
                providerId = hustle.providerId, category = hustle.category, price = hustle.price,
                description = hustle.description, title = hustle.title, location = hustle.location
            )
            val requestBody = HustleModel.HustleRequest(HustleModel.HustleRequestProperties(hustleBody))
            val response = hustleApi.postHustle(hustle.providerId, requestBody).execute()

            if(response.isSuccessful) {
                var postedHustle = response.body()!!
                database.hustleDao.insert(postedHustle)
            } else {
                Log.w(TAG, "Post Hustle failed")
                Log.d(TAG, response.toString())
            }
        }
    }

    /**
     * Refresh all local data with updates from the server
     */
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

        /**
         * Get an instance of the main repository
         */
        fun getInstance(database: MainDatabase, application: Application) : MainRepository {
            if(instance == null) {
                instance = MainRepository(database, application)
            }

            return instance!!
        }
    }
}