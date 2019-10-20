package com.example.myapplication.hustlrHub

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.myapplication.R

class HustlrHubFragment : Fragment() {

    companion object {
        fun newInstance() = HustlrHubFragment()
    }

    private lateinit var viewModel: HustlrHubViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.hustlr_hub_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HustlrHubViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
