package com.example.myapplication.hustlrHub

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.myapplication.database.MainDatabase
import com.example.myapplication.database.MainRepository
import com.example.myapplication.database.model.Hustle
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

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
