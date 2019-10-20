package com.example.myapplication.availablehustles


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.example.myapplication.databinding.FragmentAvailableHustlesBinding

/**
 * A simple [Fragment] subclass.
 */
class AvailableHustlesFragment : Fragment() {
    private lateinit var searchBar: SearchView
    private lateinit var binding: FragmentAvailableHustlesBinding
    private lateinit var hustlesViewModel: HustlesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAvailableHustlesBinding.inflate(inflater, container, false)

        searchBar = binding.searchBar

        // Get a reference to the ViewModel associated with this activity
        hustlesViewModel = ViewModelProviders.of(
            this
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

        return binding.root
    }


}
