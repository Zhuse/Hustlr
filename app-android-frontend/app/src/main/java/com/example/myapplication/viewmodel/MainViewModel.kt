package com.example.myapplication.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class MainViewModel private constructor() : ViewModel() {

    val serverHelloWorld: MutableLiveData<String> by lazy { MutableLiveData<String>() }


    // Updates the serverHelloWorld LiveData
    fun getServerHelloWorld() {

        // TODO: This is the part where we should either make the network request or delegate to
        // persistence repository to make the request for us

        if (serverHelloWorld.value == null || serverHelloWorld.value == "") {
            serverHelloWorld.value = "[Local] Hello Android"
        } else {
            serverHelloWorld.value = ""
        }
    }

    // Singleton Logic
    companion object {
        private lateinit var instance: MainViewModel

        fun getInstance() : MainViewModel {
            if(!this::instance.isInitialized) {
                instance = MainViewModel()
            }

            return instance
        }
    }
}

fun main(){

}