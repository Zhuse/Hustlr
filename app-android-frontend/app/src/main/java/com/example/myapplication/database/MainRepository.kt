package com.example.myapplication.database

import android.accounts.AccountManager
import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.myapplication.HustleCategory
import com.example.myapplication.auth.api.UserApi
import com.example.myapplication.database.api.HustleApi
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
    var hustleBids: LiveData<List<HustleBid>> = database.hustleBidDao.getAll()
    var hustlrs: LiveData<List<Hustlr>> = database.hustlrDao.getAll()

    private val accountManager: AccountManager by lazy { AccountManager.get(application) }

//    var myHustlrId: Long = 1 // TODO: Change this
    var myHustlrId: String = if(accountManager.accounts.isEmpty()) "5dae54b559570812dd6c73ca" else accountManager.accounts[0].type

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
//            getHustlesDisposable = hustleApi
//                .getHustlesByUserMatched(myHustlrId)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe( { hustles ->
//                    database.hustleDao.insertAll(hustles.properties.hustles)
//                },
//                    {err ->
//                        err.printStackTrace()
//                        Log.w(TAG, "Refreshing Hustles Failed. Err: ${err}")
//                    }
//                )

            val response = hustleApi
                .getHustlesByUserMatched(myHustlrId).execute()

            if(response.isSuccessful) {
                var newHustles = response.body()
                database.hustleDao.insertAll(newHustles!!.properties.hustles)
            } else if(!response.isSuccessful) {
                Log.i(TAG, "Get Hustles failed")
                Log.w(TAG, response.errorBody().toString())
            }


//            // For now use test hustles
//            val newHustles = testHustles()
//
//            // Store data into the database
//            database.hustleDao.insertAll(newHustles)
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
     * Post a new Hustle and store it in the offline database
     */
    suspend fun postHustle(hustle: Hustle) {
        withContext(Dispatchers.IO) {
            // Post the hustle via REST Api
//            postHustleDisposable = hustleApi
//                .postHustle(hustle.providerId)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe( { postedHustle ->
//                    database.hustleDao.insert(postedHustle)
//                },
//                    {err ->
//                        Log.w(TAG, "Posting Hustle failed. Err: ${err}")
//                    }
//                )
            val response = hustleApi.postHustle(hustle.providerId, hustle).execute()

            if(response.isSuccessful) {
                var postedHustle = response.body()!!
                database.hustleDao.insert(postedHustle)
            } else {
                Log.w(TAG, "Post Hustle failed")
                Log.d(TAG, response.errorBody().toString())
            }

//            // Get the posted hustle (with the correct ID)
//            val postedHustle = hustle
//
//            // Store it into the local database
//            database.hustleDao.insert(hustle)
        }
    }

    fun testHustles() : List<Hustle> {
        val provider = Hustlr(name = "John Wick")
        val list = mutableListOf<Hustle>()

        for(i in 1..8) {
            val hustle = Hustle(i.toString(), "Help Moving Out", providerId =  provider._id, price = 25, description = "I need help moving my stuff out of the house especially after tomorrow night",
                category = HustleCategory.homework.toString(), location = "1234 Safe St", status = HustleStatus.posted.toString()
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

        fun getInstance(database: MainDatabase, application: Application) : MainRepository {
            if(instance == null) {
                instance = MainRepository(database, application)
            }

            return instance!!
        }
    }
}