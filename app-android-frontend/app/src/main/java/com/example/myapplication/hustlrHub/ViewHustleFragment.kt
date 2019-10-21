package com.example.myapplication.hustlrHub


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders

import com.example.myapplication.R
import com.example.myapplication.database.model.Hustle
import com.example.myapplication.databinding.FragmentViewHustleBinding

/**
 * A simple [Fragment] subclass.
 */
class ViewHustleFragment : Fragment() {

    companion object {
        fun newInstance() = ViewHustleFragment()
    }

    private lateinit var binding: FragmentViewHustleBinding
    private lateinit var vm: HustlrHubViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentViewHustleBinding.inflate(inflater, container, false)

        // Get a reference to the viewmodel
        vm = ViewModelProviders.of(this).get(HustlrHubViewModel::class.java)
        val hustleId: Long = arguments!!.getLong("hustleId")
        val hustle = vm.getHustle(hustleId)

        // Initialize Fields
        initializeViewFields(hustle)

        return binding.root
    }

    private fun initializeViewFields(hustle: Hustle) {
        binding.titleText.text = hustle.title
        binding.descriptionText.text = hustle.description
        binding.offerPriceValue.setText(hustle.price.toString())
        binding.locationText.text = hustle.location
    }


}
