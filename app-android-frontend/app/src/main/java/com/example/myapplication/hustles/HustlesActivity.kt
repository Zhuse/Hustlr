package com.example.myapplication.hustles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//import androidx.appcompat.widget.SearchView
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.R
import com.example.myapplication.database.MainDatabase
import com.example.myapplication.databinding.ActivityHustlesBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

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
        val viewModelFactory =
            HustlesViewModelFactory(applicationVal)

        // Get a reference to the ViewModel associated with this activity
        hustlesViewModel = ViewModelProviders.of(
            this, viewModelFactory
        ).get(HustlesViewModel::class.java)

        // associate the adapter with the recycler view
        val adapter = HustlesListAdapter()
        binding.hustlesList.adapter = adapter

        // observe the hustles variable
        hustlesViewModel.hustles.observe(this, Observer {
            it?.let {
                adapter.data = it
            }
        })

        // Navbar Things
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_hustlr_hub
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}
