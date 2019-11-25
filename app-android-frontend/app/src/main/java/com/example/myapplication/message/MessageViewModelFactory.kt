package com.example.myapplication.message

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

/**
 * ViewModelFactory for LoginViewModel to instantiate AndroidViewModel
 */
class MessageViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>) : T {
        if(modelClass.isAssignableFrom(MessageViewModel::class.java)) {
            return MessageViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}