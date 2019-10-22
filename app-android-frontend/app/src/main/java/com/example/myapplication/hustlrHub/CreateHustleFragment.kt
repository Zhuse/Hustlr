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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCreateHustleBinding.inflate(inflater, container, false)

        // Get a reference to the view model
        vm = ViewModelProviders.of(this).get(HustlrHubViewModel::class.java)

        binding.createNewHustleButton.setOnClickListener { view ->
            if(validateInput()) {
                handleSubmitInput()
                Toast.makeText(view.context, "Creating Hustle...", Toast.LENGTH_LONG)
                    .show()
                view!!.findNavController().navigate(R.id.action_navigation_create_hustle_to_navigation_hustlr_hub2)
            } else {
                Toast.makeText(view.context, "Error in the inputs", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        return binding.root
    }

    private fun validateInput() : Boolean {
        return true // TODO: Implement this
    }

    private fun handleSubmitInput() {
        vm.postNewHustle(
            title = binding.titleText.text.toString(),
            description = binding.descriptionText.text.toString(),
            price = binding.offerPriceValue.text.toString().toInt(),
            location = binding.locationText.text.toString(),
            categories = listOf<String>(HustleCategory.homework.toString())
        )
    }


}
