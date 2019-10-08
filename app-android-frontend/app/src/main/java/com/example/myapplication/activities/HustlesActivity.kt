package com.example.myapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//import androidx.appcompat.widget.SearchView
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.myapplication.R
import com.example.myapplication.database.MainDatabase
import com.example.myapplication.databinding.ActivityHustlesBinding
import com.example.myapplication.viewmodels.HustlesViewModel
import com.example.myapplication.viewmodels.HustlesViewModelFactory

class HustlesActivity : AppCompatActivity() {
    private lateinit var searchBar: SearchView
    private lateinit var binding: ActivityHustlesBinding
    private lateinit var hustlesViewModel: HustlesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_hustles)

        searchBar = binding.searchBar

        // Create an instance of the ViewModel Factory
        val applicationVal = requireNotNull(application)
        val viewModelFactory = HustlesViewModelFactory(applicationVal)

        // Get a reference to the ViewModel associated with this activity
        val db = MainDatabase.getInstance(application!!)


        hustlesViewModel = ViewModelProviders.of(
            this, viewModelFactory
        ).get(HustlesViewModel::class.java)
    }
}
