package com.example.myapplication.message


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.MSG_DIALOG
import com.example.myapplication.MSG_TOAST
import com.example.myapplication.R
import com.example.myapplication.message.model.Message
import com.example.myapplication.message.model.User
import com.example.myapplication.message.model.UserType
import kotlinx.android.synthetic.main.activity_message_list.*
import kotlinx.android.synthetic.main.activity_message_list.view.*

/**
 * A simple [Fragment] subclass.
 */
class MessageListFragment : Fragment() {
    private lateinit var root: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        activity?.actionBar?.setDisplayHomeAsUpEnabled(true)
        root = inflater.inflate(R.layout.activity_message_list, container, false)
        initializeViewModel()
        return root
    }

    private lateinit var messageRecycler: RecyclerView
    private lateinit var messageAdapter: MessageListAdapter
    private lateinit var vm: MessageViewModel

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_message_list)
//        setActionBar(findViewById(R.id.message_toolbar))
//
//        actionBar!!.setDisplayHomeAsUpEnabled(true)
//        initializeViewModel()
//    }

    override fun onStart() {
        super.onStart()

        vm.openSocket()
        initializeRecyclerView()
        button_chatbox_send.setOnClickListener {
            val message = it.edittext_chatbox.toString()
            vm.sendMessage(message)
            messageAdapter.addItem(Message(message, User("Me", UserType.SENDER)))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.let {
            return when (it.itemId) {
                android.R.id.home -> {
//                    finish()
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()

        vm.closeSocket()
    }

    private fun initializeViewModel() {
//        vm = ViewModelProvider.NewInstanceFactory().create(MessageViewModel::class.java)
        vm = ViewModelProviders.of(this).get(MessageViewModel::class.java)

        vm.messageData.observe(this, Observer { it?.let { messageAdapter.addItem(it) } })
        vm.notificationData.observe(this, Observer {
            it?.let {
                when (it.first) {
                    MSG_TOAST -> {
                        Toast.makeText(context, it.second, Toast.LENGTH_SHORT).show()
                    }
                    MSG_DIALOG -> {
                        // TODO:
                    }
                    else -> {

                    }
                }
            }
        })
    }

    private fun initializeRecyclerView() {
        messageAdapter = MessageListAdapter()
        messageRecycler = root.findViewById<RecyclerView>(R.id.reyclerview_message_list).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = messageAdapter
        }
    }


}
