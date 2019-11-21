package com.example.myapplication.hustlrHub

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

/**
 * Available Hustle ViewHolder
 */
class HustleBidViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title: TextView = itemView.findViewById(R.id.title)
    val descriptionSnippet: TextView = itemView.findViewById(R.id.descriptionSnippet)
    val price: TextView = itemView.findViewById(R.id.price)
    val bidStatus: TextView = itemView.findViewById(R.id.bidStatus)
}