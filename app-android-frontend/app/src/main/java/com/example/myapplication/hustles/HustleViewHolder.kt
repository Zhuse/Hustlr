package com.example.myapplication.hustles

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import kotlinx.android.synthetic.main.list_item_hustle.*

class HustleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title: TextView = itemView.findViewById(R.id.title)
    val descriptionSnippet: TextView = itemView.findViewById(R.id.descriptionSnippet)
    val price: TextView = itemView.findViewById(R.id.price)
}