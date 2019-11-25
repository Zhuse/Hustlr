package com.example.myapplication.hustlrHub

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.database.model.Hustle
import com.example.myapplication.database.model.HustleBid
import kotlinx.android.synthetic.main.list_item_hustle_bid.view.*
import java.lang.StringBuilder

/**
 * Adapter for Available Hustles List
 */
class HustleBidListAdapter(private val myHustlrId: String,
                           val bidAcceptedHandler: ((bid: HustleBid) -> Unit)?,
                           val ignoreBidHandler: ((bid: HustleBid) -> Unit)?): RecyclerView.Adapter<HustleBidViewHolder>() {
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
        if(hustles.isEmpty()) return
        val filteredHustles = hustles.filter { hustle -> hustle._id == bid.hustleId }
        if(filteredHustles.isEmpty()) {
            return
        }
//        val hustle = hustles.first { hustle -> hustle._id == bid.hustleId
        val hustle = filteredHustles[0]

        val descriptionEndIndex: Int = if(bid.description.length < 25) bid.description.length else 25
//        val descriptionSnippet = StringBuilder(item.description.substring(0, 30))
        val descriptionSnippet = StringBuilder(bid.description.substring(0, descriptionEndIndex))
        descriptionSnippet.append("...")

        holder.title.text = hustle.title
        holder.price.text = bid.bidCost.toString()
        holder.descriptionSnippet.text = descriptionSnippet.toString()

        val actionButtonVisibility = getAcceptRejectButtonVisibility(bid, hustle)
        holder.itemView.acceptBidButton.visibility = actionButtonVisibility
        holder.itemView.ignoreBidButton.visibility = actionButtonVisibility

        if(actionButtonVisibility == View.VISIBLE) {
            holder.itemView.acceptBidButton.setOnClickListener {
                Toast.makeText(it.context, "Accepting bid...", Toast.LENGTH_SHORT).show()
                bidAcceptedHandler?.invoke(bid)
            }

            holder.itemView.ignoreBidButton.setOnClickListener {
                Toast.makeText(it.context, "Rejecting bid...", Toast.LENGTH_SHORT).show()
                ignoreBidHandler?.invoke(bid)
            }
        }

        val statusText = determineStatusText(bid, hustle)
        holder.bidStatus.text = statusText
    }

    private fun getAcceptRejectButtonVisibility(bid: HustleBid, hustle: Hustle) : Int {
        return if(hustle.providerId.contentEquals(myHustlrId) && hustle.hustlrId == null) View.VISIBLE else View.GONE
    }

    private fun determineStatusText(bid: HustleBid, hustle: Hustle) : String {

        return if(hustle.providerId.contentEquals(myHustlrId)) {
            when {
                hustle.hustlrId == null -> "New Bid"
                hustle.hustlrId == bid.userId -> "Bid Awarded"
                else -> "Not Awarded"
            }
        } else {
            when {
                hustle.hustlrId == null -> "Pending Decision"
                hustle.hustlrId == myHustlrId -> "Bid Accepted"
                else -> "Bid Rejected"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : HustleBidViewHolder {
        val layoutInflator = LayoutInflater.from(parent.context)
        val view = layoutInflator
            .inflate(R.layout.list_item_hustle_bid, parent, false) as View
        return HustleBidViewHolder(view)
    }
}