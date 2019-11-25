package com.example.myapplication.hustlrHub

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.findNavController

import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentHustlrHubBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * Simple HustlrHub Fragment
 */
class HustlrHubFragment : Fragment() {

    companion object {
        /**
         * Get a new instance of the HustlrHub Fragment
         */
        fun newInstance() = HustlrHubFragment()
    }

    private lateinit var viewModel: HustlrHubViewModel
    private lateinit var binding: FragmentHustlrHubBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHustlrHubBinding.inflate(inflater, container, false)
        viewModel = ViewModelProviders.of(this).get(HustlrHubViewModel::class.java)

        val createHustleButton: FloatingActionButton = binding.createNewHustleButton
        createHustleButton.setOnClickListener {view: View ->
            view.findNavController()
                .navigate(R.id.action_navigation_hustlr_hub_to_navigation_create_hustle)
        }

        val bidsReceivedAdapter = HustleBidListAdapter(viewModel.myHustlrId, viewModel::acceptBid, viewModel::ignoreBid)
        val bidsSubmittedAdapter = HustleBidListAdapter(viewModel.myHustlrId, null, null)

        binding.bidsReceivedList.adapter = bidsReceivedAdapter
        binding.bidsSubmittedList.adapter = bidsSubmittedAdapter

        viewModel.hustles.observe(this, Observer {
            it?.let {
                bidsReceivedAdapter.hustles = it
                bidsSubmittedAdapter.hustles = it
            }
        })

        viewModel.bidsReceived.observe(this, Observer {
            it?.let {
                bidsReceivedAdapter.bids = it
            }
        })

        viewModel.bidsSubmitted.observe(this, Observer {
            it?.let {
                bidsSubmittedAdapter.bids = it
            }
        })

        return binding.root
    }
}
