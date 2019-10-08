package com.example.myapplication.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.myapplication.database.MainDatabase
import com.example.myapplication.database.MainRepository

class HustlesViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: MainRepository = MainRepository
        .getInstance(MainDatabase.getInstance(application))

    // Do a bunch of other view model kinda stuff
}