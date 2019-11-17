package com.example.myapplication.hustlrHub


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController

import com.example.myapplication.R
import com.example.myapplication.database.model.Hustle
import com.example.myapplication.database.model.HustleBid
import com.example.myapplication.databinding.FragmentViewHustleBinding
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

/**
 * A simple [Fragment] subclass.
 */
class ViewHustleFragment : Fragment() {

    companion object {
        /**
         * Get a new instance of the ViewHustle Fragment
         */
        fun newInstance() = ViewHustleFragment()
    }

    private lateinit var binding: FragmentViewHustleBinding
    private lateinit var vm: HustlrHubViewModel
    private lateinit var targetHustle: Hustle
    private lateinit var categoryChipGroup: ChipGroup

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentViewHustleBinding.inflate(inflater, container, false)

        // Get a reference to the viewmodel
        vm = ViewModelProviders.of(this).get(HustlrHubViewModel::class.java)
        val hustleId: String = arguments!!.getString("hustleId")
        targetHustle = vm.getHustle(hustleId)

        // Initialize Fields
        initializeViewFields(targetHustle)

        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.let {
            when(it.itemId) {
                android.R.id.home -> {
                    findNavController().navigate(R.id.action_navigation_view_hustle_to_navigation_available_hustles)
                }
                else -> {

                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initializeViewFields(hustle: Hustle) {
        binding.titleText.text = hustle.title
        binding.descriptionText.text = hustle.description
        binding.offerPriceValue.setText(hustle.price.toString())
        binding.locationText.text = hustle.location

        binding.incrementValueButton.setOnClickListener { view ->
            var newPrice = binding.offerPriceValue.text.toString().toInt()
            newPrice += 1
            binding.offerPriceValue.setText(newPrice.toString())
        }

        binding.decrementValueButton.setOnClickListener { view ->
            var newPrice = binding.offerPriceValue.text.toString().toInt()
            newPrice -= 1
            binding.offerPriceValue.setText(newPrice.toString())
        }

        binding.submitHustleBidButton.setOnClickListener { view: View ->
            handleSubmit()
            Toast.makeText(view.context, "Submitting Bid...", Toast.LENGTH_LONG)
                .show()
            view!!.findNavController().navigate(R.id.action_navigation_view_hustle_to_navigation_available_hustles)
        }

        categoryChipGroup = binding.categoriesChipGroup
        val category = Chip(context)
        category.text = hustle.category
        category.isCloseIconVisible = false
        category.isCheckable = false
        category.isClickable = false
        categoryChipGroup.addView(category as View)
    }

    private fun handleSubmit() {
        vm.postHustleBid(
            hustleId = targetHustle._id,
            bidPrice = binding.offerPriceValue.text.toString().toInt()
        )
    }


}
