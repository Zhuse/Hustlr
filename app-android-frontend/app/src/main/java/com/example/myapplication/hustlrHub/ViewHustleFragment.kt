package com.example.myapplication.hustlrHub


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentViewHustleBinding

/**
 * A simple [Fragment] subclass.
 */
class ViewHustleFragment : Fragment() {

    companion object {
        fun newInstance() = ViewHustleFragment()
    }

    private lateinit var binding: FragmentViewHustleBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentViewHustleBinding.inflate(inflater, container, false)

        return binding.root
    }


}
