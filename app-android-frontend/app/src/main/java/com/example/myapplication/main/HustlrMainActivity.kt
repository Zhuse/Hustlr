package com.example.myapplication.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.R
import com.example.myapplication.availablehustles.HustlesListAdapter
import com.example.myapplication.availablehustles.HustlesViewModel
import com.example.myapplication.availablehustles.HustlesViewModelFactory
import com.example.myapplication.databinding.ActivityHustlrMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class HustlrMainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHustlrMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_hustlr_main)



        // Navbar Things
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_available_hustles, R.id.navigation_hustlr_hub
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}
