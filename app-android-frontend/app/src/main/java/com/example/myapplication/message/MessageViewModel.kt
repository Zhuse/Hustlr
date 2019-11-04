package com.example.myapplication.message

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.BASE_URL
import com.example.myapplication.message.model.Message
import com.example.myapplication.message.model.User
import com.example.myapplication.message.model.UserType
import com.github.nkzawa.emitter.Emitter
import com.github.nkzawa.socketio.client.IO
import com.github.nkzawa.socketio.client.Socket
import org.json.JSONException
import java.net.URISyntaxException

/**
 * View Model class that prepares and manages data for MessageListFragment.
 * This class manages the socket connection for chat and sending/receiving messages.
 */
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
            messageData.postValue(Message(message, User(username, UserType.SENDER)))
        } catch (e: JSONException) {
            Log.e(TAG, "Error parsing message data", e)
        }
    }

    override fun onCleared() {
        super.onCleared()

        closeSocket()
    }

    /**
     * Open a connection to the socket.
     * This connection will need to be closed with a call to closeSocket().
     */
    fun openSocket() {
        try {
            messageSocket = IO.socket(BASE_URL)
        } catch (e: URISyntaxException) {
            Log.e(TAG, "Could not open socket", e)
        }

        messageSocket!!.apply {
            on(EVENT_MESSAGE, onNewMessage)
            connect()
        }
    }

    /**
     * Send a message to the socket.
     *
     * @param message - message to be sent to socket
     */
    fun sendMessage(message: String) {
        messageSocket!!.emit(EVENT_MESSAGE, message)
    }

    /**
     * Close the connection to the socket.
     * A connection to the socket must have been opened with a call to openSocket() before this call.
     */
    fun closeSocket() {
        messageSocket!!.apply {
            disconnect()
            off(EVENT_MESSAGE, onNewMessage)
        }
    }

    companion object {
        const val TAG = "MessageViewModel"
        const val EVENT_MESSAGE = "chat message"
    }
}