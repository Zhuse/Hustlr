package com.example.myapplication.hustlrHub

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.database.MainDatabase
import com.example.myapplication.database.MainRepository
import com.example.myapplication.database.model.Hustle
import com.example.myapplication.database.model.HustleBid
import com.example.myapplication.database.model.HustleStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * HustlrHub ViewModel
 */
class HustlrHubViewModel(application: Application) : AndroidViewModel(application) {
    // TODO: Implement the ViewModel
    private val repository = MainRepository.getInstance(MainDatabase.getInstance(application), application)

    private var viewModelJob: Job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val backgroundScope = CoroutineScope(Dispatchers.IO + viewModelJob)

    val hustles = repository.hustles
    val biddableHustles = repository.biddableHustles

    var bidsSubmitted: LiveData<List<HustleBid>> = repository.bidsSubmitted
    var bidsReceived: LiveData<List<HustleBid>> = repository.bidsReceived

    val myHustlrId = repository.myHustlrId

    init {
        initalize()
    }

    /**
     * Initial setup for viewmodel
     */
    private fun initalize() {
        backgroundScope.launch {
            refreshHustleBids()
        }
    }


    /**
     * Get a hustle by specifying its ID
     * @param id Must be a valid ID for a hustle already cached locally
     */
    fun getHustle(id: String) : Hustle {
        val hustlesList = hustles.value
        return hustlesList!!.filter { hustle -> hustle._id.contentEquals(id) }[0]
    }

    /**
     * Get a biddable hustle by specifying its ID
     * @param id Must be a valid ID for a hustle already cached locally
     */
    fun getBiddableHustle(id: String) : Hustle {
        val hustlesList = biddableHustles.value
        return hustlesList!!.filter { hustle -> hustle._id.contentEquals(id) }[0]
    }

    /**
     * Post a new hustle bid
     */
    fun postHustleBid(hustleId: String, bidCost: Int, description: String) {
        backgroundScope.launch {
            repository.postHustleBid(description, bidCost, hustleId)
        }
    }

    /**
     * Set a bid as ignored
     */
    fun ignoreBid(bid: HustleBid) {
        backgroundScope.launch {
            repository.ignoreHustleBid(bid)
        }
    }

    /**
     * Accept a bid
     */
    fun acceptBid(bid: HustleBid) {
        backgroundScope.launch {
            repository.acceptHustleBid(bid)
        }
    }

    /**
     * Post a new hustle
     */
    fun postNewHustle(title: String, description: String, price: Int,
                      location: String, category: String) {
        val hustle: Hustle = Hustle(title = title, providerId = repository.myHustlrId,
            price = price, description = description, location = location, category = category,
            status = HustleStatus.posted.toString(), bids = listOf(), hustlrId = "")
        backgroundScope.launch {
            repository.postHustle(hustle)
        }
    }

    /**
     * Refresh hustle bids
     */
    private suspend fun refreshHustleBids() = repository.refreshHustleBids()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    companion object {
        val TAG = this::class.java.canonicalName
    }
}
