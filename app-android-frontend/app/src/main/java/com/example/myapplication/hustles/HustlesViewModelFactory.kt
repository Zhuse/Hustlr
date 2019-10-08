package com.example.myapplication.hustles

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class HustlesViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>) : T {
        if(modelClass.isAssignableFrom(HustlesViewModel::class.java)) {
            return HustlesViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}