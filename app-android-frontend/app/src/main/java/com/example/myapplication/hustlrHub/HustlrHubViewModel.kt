package com.example.myapplication.hustlrHub

import android.app.Application
import androidx.lifecycle.AndroidViewModel
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

    private val hustles = repository.hustles


    /**
     * Get a hustle by specifying its ID
     * @param id Must be a valid ID for a hustle already cached locally
     */
    fun getHustle(id: String) : Hustle {
        val hustlesList = hustles.value
        return hustlesList!!.filter { hustle -> hustle._id.contentEquals(id) }[0]
    }

    /**
     * Post a new hustle bid
     */
    fun postHustleBid(hustleId: String, bidPrice: Int) {
        val bid: HustleBid = HustleBid(hustleId = hustleId, bidPrice = bidPrice,
            bidderId = repository.myHustlrId)
        backgroundScope.launch {
            repository.postHustleBid(bid)
        }
    }

    /**
     * Post a new hustle
     */
    fun postNewHustle(title: String, description: String, price: Int,
                      location: String, category: String) {
        val hustle: Hustle = Hustle(title = title, providerId = repository.myHustlrId,
            price = price, description = description, location = location, category = category,
            status = HustleStatus.posted.toString())
        backgroundScope.launch {
            repository.postHustle(hustle)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
