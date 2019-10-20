package com.example.myapplication.availablehustles

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        val descriptionSnippet = StringBuilder(item.description.substring(0, 30))
        descriptionSnippet.append("...")

        holder.title.text = item.title
        holder.price.text = item.price.toString()
        holder.descriptionSnippet.text = descriptionSnippet.toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : AvailableHustleViewHolder {
        val layoutInflator = LayoutInflater.from(parent.context)
        val view = layoutInflator
            .inflate(R.layout.list_item_available_hustle, parent, false) as View
        return AvailableHustleViewHolder(view)
    }
}