package com.example.myapplication.message

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.message.model.Message

/**
 * ViewHolder for MessageListAdapter.
 * Binds data for chat bubbles such as user name and message.
 *
 * @param itemView - chat bubble item view
 */
class MessageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val nameText: TextView = itemView.findViewById(R.id.text_message_name)
    private val messageText: TextView = itemView.findViewById(R.id.text_message_body)

    /**
     * Bind a message data to this message VH.
     *
     * @param message - message object
     */
    fun bind(message: Message) {
        nameText.text = message.sender.name
        messageText.text = message.message
    }
}