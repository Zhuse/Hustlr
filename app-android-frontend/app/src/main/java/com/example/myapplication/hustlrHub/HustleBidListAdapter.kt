package com.example.myapplication.hustlrHub

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.database.model.Hustle
import com.example.myapplication.database.model.HustleBid
import java.lang.StringBuilder

/**
 * Adapter for Available Hustles List
 */
class HustleBidListAdapter(private val myHustlrId: String): RecyclerView.Adapter<HustleBidViewHolder>() {
    var bids = listOf<HustleBid>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var hustles = listOf<Hustle>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() : Int = bids.size

    override fun onBindViewHolder(holder: HustleBidViewHolder, position: Int) {
        val bid = bids[position]
        val hustle = hustles.first { hustle -> hustle._id == bid.hustleId }

        val descriptionEndIndex: Int = if(bid.description.length < 25) bid.description.length else 25
//        val descriptionSnippet = StringBuilder(item.description.substring(0, 30))
        val descriptionSnippet = StringBuilder(bid.description.substring(0, descriptionEndIndex))
        descriptionSnippet.append("...")

        holder.title.text = hustle.title
        holder.price.text = bid.bidCost.toString()
        holder.descriptionSnippet.text = descriptionSnippet.toString()

        var statusText = ""
        if(hustle.providerId.contentEquals(myHustlrId)) {
            statusText = when {
                hustle.hustlrId == null -> "New Bid"
                hustle.hustlrId == bid.userId -> "Bid Awarded"
                else -> "Not Awarded"
            }
        } else {
            statusText = when {
                hustle.hustlrId == null -> "Pending Decision"
                hustle.hustlrId == myHustlrId -> "Bid Accepted"
                else -> "Bid Rejected"
            }
        }
        holder.bidStatus.text = statusText

//        holder.itemView.setOnClickListener { view ->
//            var bundle: Bundle = Bundle()
//            bundle.putString("hustleId", item._id)
//            view.findNavController().navigate(R.id.action_navigation_available_hustles_to_navigation_view_hustle, bundle)
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : HustleBidViewHolder {
        val layoutInflator = LayoutInflater.from(parent.context)
        val view = layoutInflator
            .inflate(R.layout.list_item_hustle_bid, parent, false) as View
        return HustleBidViewHolder(view)
    }
}