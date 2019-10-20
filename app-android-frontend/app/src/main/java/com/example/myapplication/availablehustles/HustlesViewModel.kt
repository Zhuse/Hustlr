package com.example.myapplication.availablehustles

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.myapplication.database.MainDatabase
import com.example.myapplication.database.MainRepository
import com.example.myapplication.database.model.Hustle

class HustlesViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: MainRepository = MainRepository
        .getInstance(MainDatabase.getInstance(application))

    // Do a bunch of other view model kinda stuff

    val hustles: LiveData<List<Hustle>> = repository.getAllHustles()
}