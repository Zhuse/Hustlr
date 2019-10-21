package com.example.myapplication.hustlrHub


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentCreateHustleBinding

/**
 * A simple [Fragment] subclass.
 */
class CreateHustleFragment : Fragment() {

    companion object {
        fun newInstance() = CreateHustleFragment()
    }

    private lateinit var  binding: FragmentCreateHustleBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCreateHustleBinding.inflate(inflater, container, false)

        return binding.root
    }


}
