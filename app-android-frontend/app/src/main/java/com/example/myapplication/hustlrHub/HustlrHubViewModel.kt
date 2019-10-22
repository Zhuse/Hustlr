package com.example.myapplication.hustlrHub

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.myapplication.database.MainDatabase
import com.example.myapplication.database.MainRepository
import com.example.myapplication.database.model.Hustle
import com.example.myapplication.database.model.HustleBid
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HustlrHubViewModel(application: Application) : AndroidViewModel(application) {
    // TODO: Implement the ViewModel
    private val repository = MainRepository.getInstance(MainDatabase.getInstance(application))

    private var viewModelJob: Job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val backgroundScope = CoroutineScope(Dispatchers.IO + viewModelJob)

    private val hustles = repository.hustles


    /**
     * Get a hustle by specifying its ID
     * @param id Must be a valid ID for a hustle already cached locally
     */
    fun getHustle(id: Long) : Hustle {
        val hustlesList = hustles.value
        return hustlesList!!.filter { hustle -> hustle.hustleId == id }[0]
    }

    fun postHustleBid(hustleId: Long, bidPrice: Int, providerId: Long) {
        val bid: HustleBid = HustleBid(hustleId = hustleId, bidPrice = bidPrice,
            providerId = providerId, bidderId = repository.myHustlrId)
        backgroundScope.launch {
            repository.postHustleBid(bid)
        }
    }

    fun postNewHustle(title: String, description: String, price: Int,
                      location: String, categories: List<String>) {
        val hustle: Hustle = Hustle(title = title, providerId = repository.myHustlrId,
            price = price, description = description, location = location, categories = categories)
        backgroundScope.launch {
            repository.postHustle(hustle)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
