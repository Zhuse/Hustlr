package com.example.myapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//import androidx.appcompat.widget.SearchView
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityHustlesBinding

class HustlesActivity : AppCompatActivity() {
    private lateinit var searchBar: SearchView
    private lateinit var binding: ActivityHustlesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_hustles)

        searchBar = binding.searchBar

    }
}
