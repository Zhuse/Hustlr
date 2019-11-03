package com.example.myapplication.hustlrHub


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.myapplication.HustleCategory

import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentCreateHustleBinding
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import kotlinx.android.synthetic.main.fragment_view_hustle.*
import kotlinx.android.synthetic.main.list_item_available_hustle.*

/**
 * A simple [Fragment] subclass.
 */
class CreateHustleFragment : Fragment() {

    companion object {
        fun newInstance() = CreateHustleFragment()
    }

    private lateinit var binding: FragmentCreateHustleBinding
    private lateinit var vm: HustlrHubViewModel

    // Chips
    private lateinit var categoriesChipGroup: ChipGroup
    private lateinit var otherChip: Chip
    private lateinit var liftingChip: Chip
    private lateinit var homeworkChip: Chip
    private lateinit var transportationChip: Chip
    private var selectedCategory: HustleCategory? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCreateHustleBinding.inflate(inflater, container, false)

        // Get a reference to the view model
        vm = ViewModelProviders.of(this).get(HustlrHubViewModel::class.java)

        categoriesChipGroup = binding.categoriesChipGroup
        initializeCategories()

        binding.createNewHustleButton.setOnClickListener { view ->
            if(validateInput()) {
                handleSubmitInput()
                Toast.makeText(view.context, "Creating Hustle...", Toast.LENGTH_SHORT)
                    .show()
                view!!.findNavController().navigate(R.id.action_navigation_create_hustle_to_navigation_hustlr_hub2)
            } else {
//                Toast.makeText(view.context, "Error in the inputs", Toast.LENGTH_SHORT)
//                    .show()
            }
        }

        return binding.root
    }

    private fun initializeCategories() {
        transportationChip = Chip(context)
        transportationChip.text = HustleCategory.transporation.name
        transportationChip.isCloseIconVisible = false
        transportationChip.isCheckable = true
        transportationChip.isClickable = true

        liftingChip = Chip(context)
        liftingChip.text = HustleCategory.lifting.name
        liftingChip.isCloseIconVisible = false
        liftingChip.isCheckable = true
        liftingChip.isClickable = true

        homeworkChip = Chip(context)
        homeworkChip.text = HustleCategory.homework.name
        homeworkChip.isCloseIconVisible = false
        homeworkChip.isCheckable = true
        homeworkChip.isClickable = true

        otherChip = Chip(context)
        otherChip.text = HustleCategory.other.name
        otherChip.isCloseIconVisible = false
        otherChip.isCheckable = true
        otherChip.isClickable = true


        categoriesChipGroup.addView(transportationChip as View)
        categoriesChipGroup.addView(liftingChip as View)
        categoriesChipGroup.addView(homeworkChip as View)
        categoriesChipGroup.addView(otherChip as View)
    }

    private fun validateInput() : Boolean {
        // Check chips
        if(!validateChips()) {
            Toast.makeText(context, "Incorrect number of categories selected", Toast.LENGTH_SHORT)
                .show()
            return false
        }

        return true // TODO: Implement this
    }

    private fun validateChips() : Boolean {
        var numIsChecked = 0

        if(transportationChip.isChecked) {
            selectedCategory = HustleCategory.transporation
            numIsChecked++
        }

        if(liftingChip.isChecked) {
            selectedCategory = HustleCategory.lifting
            numIsChecked++
        }

        if(homeworkChip.isChecked) {
            selectedCategory = HustleCategory.homework
            numIsChecked++
        }

        if(otherChip.isChecked) {
            selectedCategory = HustleCategory.other
            numIsChecked++
        }

        return numIsChecked == 1
    }

    private fun handleSubmitInput() {
        vm.postNewHustle(
            title = binding.titleText.text.toString(),
            description = binding.descriptionText.text.toString(),
            price = binding.offerPriceValue.text.toString().toInt(),
            location = binding.locationText.text.toString(),
            category = selectedCategory!!.name
        )
    }


}
