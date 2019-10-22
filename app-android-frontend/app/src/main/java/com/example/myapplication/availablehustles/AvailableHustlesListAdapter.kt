package com.example.myapplication.availablehustles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.database.model.Hustle
import java.lang.StringBuilder

class AvailableHustlesListAdapter: RecyclerView.Adapter<AvailableHustleViewHolder>() {
    var data = listOf<Hustle>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() : Int = data.size

    override fun onBindViewHolder(holder: AvailableHustleViewHolder, position: Int) {
        val item = data[position]

        val descriptionEndIndex: Int = if(item.description.length < 30) item.description.length else 30
//        val descriptionSnippet = StringBuilder(item.description.substring(0, 30))
        val descriptionSnippet = StringBuilder(item.description.substring(0, descriptionEndIndex))
        descriptionSnippet.append("...")

        holder.title.text = item.title
        holder.price.text = item.price.toString()
        holder.descriptionSnippet.text = descriptionSnippet.toString()

        holder.itemView.setOnClickListener { view ->
            var bundle: Bundle = Bundle()
            bundle.putString("hustleId", item._id)
            view.findNavController().navigate(R.id.action_navigation_available_hustles_to_navigation_view_hustle, bundle)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : AvailableHustleViewHolder {
        val layoutInflator = LayoutInflater.from(parent.context)
        val view = layoutInflator
            .inflate(R.layout.list_item_available_hustle, parent, false) as View
        return AvailableHustleViewHolder(view)
    }
}