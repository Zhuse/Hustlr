package com.example.myapplication.hustles

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.database.model.Hustle
import java.lang.StringBuilder

class HustlesListAdapter: RecyclerView.Adapter<HustleViewHolder>() {
    var data = listOf<Hustle>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() : Int = data.size

    override fun onBindViewHolder(holder: HustleViewHolder, position: Int) {
        val item = data[position]

        val descriptionSnippet = StringBuilder(item.description.substring(0, 30))
        descriptionSnippet.append("...")

        holder.title.text = item.title
        holder.price.text = item.price.toString()
        holder.descriptionSnippet.text = descriptionSnippet.toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : HustleViewHolder {
        val layoutInflator = LayoutInflater.from(parent.context)
        val view = layoutInflator
            .inflate(R.layout.list_item_hustle, parent, false) as View
        return HustleViewHolder(view)
    }
}