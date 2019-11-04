package com.example.myapplication.availablehustles

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.database.MainDatabase
import com.example.myapplication.database.MainRepository
import com.example.myapplication.database.model.Hustle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * ViewModel for Available Hustles View
 */
class AvailableHustlesViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: MainRepository = MainRepository
        .getInstance(MainDatabase.getInstance(application), application)

    private var viewModelJob: Job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val backgroundScope = CoroutineScope(Dispatchers.IO + viewModelJob)

    // Do a bunch of other view model kinda stuff

    var hustles: LiveData<List<Hustle>> = repository.hustles

    init {
        initialize()
    }

    private fun initialize() {
        Log.d(TAG, "Initializing AvailableHustlesViewModel")
        backgroundScope.launch {
            refreshHustles()
        }
        Log.d(TAG, "Initialization of AvailableHustlesVM Finished")
    }

    private suspend fun refreshHustles() = repository.refreshHustles()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    companion object {
        val TAG = this::class.java.canonicalName
    }
}

