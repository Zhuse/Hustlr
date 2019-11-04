package com.example.myapplication.availablehustles

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

/**
 * Avaialble Hustles ViewModel Factory
 */
class AvailableHustlesViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>) : T {
        if(modelClass.isAssignableFrom(AvailableHustlesViewModel::class.java)) {
            return AvailableHustlesViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}