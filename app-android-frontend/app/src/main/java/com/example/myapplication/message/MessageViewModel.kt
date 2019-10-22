package com.example.myapplication.message

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.MSG_TOAST
import com.example.myapplication.R
import com.example.myapplication.BASE_URL
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

    private var messageSocket: Socket? = null
    private val onNewMessage: Emitter.Listener = Emitter.Listener {
        // TODO: Add logic to differentiate between sender and receiver
        // val data: JSONObject = it[0] as JSONObject
        val username: String
        val message: String

        try {
            username = "" // data.getString("username")
            message = it[0] as String // data.getString("message")
        } catch (e: JSONException) {
            return@Listener
        }

        messageData.postValue(Message(message, User(username, UserType.SENDER)))
    }

    override fun onCleared() {
        super.onCleared()

        closeSocket()
    }

    fun openSocket() {
        try {
            messageSocket = IO.socket(BASE_URL)
        } catch (e: URISyntaxException) {
            Log.e(TAG, "Could not open socket", e)
        }

        messageSocket!!.apply {
            on("chat message", onNewMessage)
            connect()
        }
    }

    fun sendMessage(message: String) {
        messageSocket!!.emit("chat message", message)
    }

    fun closeSocket() {
        messageSocket!!.apply {
            disconnect()
            off("chat message", onNewMessage)
        }
    }

    companion object {
        const val TAG = "MessageViewModel"
    }
}