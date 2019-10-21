package com.example.myapplication.message

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.message.model.Message
import com.example.myapplication.message.model.UserType

class MessageListAdapter: RecyclerView.Adapter<MessageViewHolder>() {
    private val messageList: ArrayList<Message> = ArrayList()

    override fun getItemViewType(position: Int): Int {
        val message = messageList[position]
        return if (message.sender.userType == UserType.SENDER) {
            VIEW_TYPE_MESSAGE_SENT
        } else {
            VIEW_TYPE_MESSAGE_RECIEVE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val view: View

        return if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_message_sent, parent, false)
            MessageViewHolder(view)
        } else {
            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_message_recieved, parent, false)
            MessageViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.bind(messageList[position])
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    fun addItem(message: Message) {
        messageList.add(message)
        this.notifyItemChanged(itemCount)
    }

    companion object {
        const val VIEW_TYPE_MESSAGE_SENT = 1
        const val VIEW_TYPE_MESSAGE_RECIEVE = 2
    }
}