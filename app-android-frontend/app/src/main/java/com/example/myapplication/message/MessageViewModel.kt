package com.example.myapplication.message

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.MSG_TOAST
import com.example.myapplication.R
import com.example.myapplication.message.model.Message
import com.example.myapplication.message.model.User
import com.example.myapplication.message.model.UserType
import com.github.nkzawa.emitter.Emitter
import com.github.nkzawa.socketio.client.IO
import com.github.nkzawa.socketio.client.Socket
import org.json.JSONException
import org.json.JSONObject
import java.net.URISyntaxException

class MessageViewModel : ViewModel() {

    val messageData: MutableLiveData<Message> = MutableLiveData()
    val notificationData: MutableLiveData<Pair<Int, Int>> = MutableLiveData()

    private var messageSocket: Socket? = null
    private val onNewMessage: Emitter.Listener = Emitter.Listener {
        val data: JSONObject = it[0] as JSONObject
        val username: String
        val message: String

        try {
            username = data.getString("username")
            message = data.getString("message")
        } catch (e: JSONException) {
            return@Listener
        }

        messageData.postValue(Message(message, User(username, UserType.RECIEVER)))
    }

    override fun onCleared() {
        super.onCleared()

        closeSocket()
    }

    fun openSocket() {
        try {
            messageSocket = IO.socket("TODO")
        } catch (e: URISyntaxException) {
            notificationData.postValue(Pair(MSG_TOAST, R.string.error_message))
        }
    }

    fun sendMessage(message: String) {
        messageSocket!!.emit("new message", message)
    }

    fun closeSocket() {
        messageSocket!!.apply {
            disconnect()
            off("new message", onNewMessage)
        }
    }
}