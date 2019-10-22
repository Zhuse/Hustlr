package com.example.myapplication.message


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.message.model.Message
import com.example.myapplication.message.model.User
import com.example.myapplication.message.model.UserType
import kotlinx.android.synthetic.main.fragment_message_list.*
import kotlinx.android.synthetic.main.fragment_message_list.view.*

/**
 * A simple [Fragment] subclass.
 */
class MessageListFragment : Fragment() {
    private lateinit var root: View

    private lateinit var messageRecycler: RecyclerView
    private lateinit var vm: MessageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        root = inflater.inflate(R.layout.fragment_message_list, container, false)
        initializeRecyclerView()
        initializeViewModel()
        return root
    }

    override fun onStart() {
        super.onStart()

        vm.openSocket()
        button_chatbox_send.setOnClickListener {
            vm.sendMessage(edittext_chatbox.text.toString())
            edittext_chatbox.text.clear()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        vm.closeSocket()
    }

    private fun initializeViewModel() {
        vm = ViewModelProviders.of(this).get(MessageViewModel::class.java)

        vm.messageData.observe(this, Observer {
            it?.let {
                (messageRecycler.adapter as MessageListAdapter).apply {
                    addItem(it)
                    messageRecycler.smoothScrollToPosition(itemCount - 1)
                }
            }
        })
    }

    private fun initializeRecyclerView() {
        val layout = LinearLayoutManager(context)
        layout.stackFromEnd = true

        messageRecycler = root.findViewById<RecyclerView>(R.id.reyclerview_message_list).apply {
            setHasFixedSize(true)
            layoutManager = layout
            adapter = MessageListAdapter()
        }
    }
}
