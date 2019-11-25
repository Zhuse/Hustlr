package com.example.myapplication.message

import android.accounts.Account
import android.accounts.AccountManager
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.BASE_URL
import com.example.myapplication.message.model.Message
import com.example.myapplication.message.model.User
import com.example.myapplication.message.model.UserType
import com.github.nkzawa.emitter.Emitter
import com.github.nkzawa.socketio.client.IO
import com.github.nkzawa.socketio.client.Socket
import org.json.JSONException
import org.json.JSONObject
import java.lang.Exception
import java.net.URISyntaxException

/**
 * View Model class that prepares and manages data for MessageListFragment.
 * This class manages the socket connection for chat and sending/receiving messages.
 */
class MessageViewModel(application: Application) : AndroidViewModel(application) {

    private val accountManager: AccountManager by lazy { AccountManager.get(application) }
    private var time: Long = 0

    private val myUserId: String = accountManager.getUserData(accountManager.accounts[0], "userId")
    private val myUsername: String = accountManager.getUserData(accountManager.accounts[0], "username")

    val messageData: MutableLiveData<Message> = MutableLiveData()


    private var messageSocket: Socket? = null
    private val onNewMessage: Emitter.Listener = Emitter.Listener {
        val data: JSONObject = it[0] as JSONObject
        val message: String
        val username: String
        val userId: String

        try {
            message = data.getString("message")
            userId = data.getString("userId")
            username = data.getString("username")

            if (userId == myUserId) {
                messageData.postValue(Message(message, User(username, UserType.SENDER)))
            } else {
                messageData.postValue(Message(message, User(username, UserType.RECIEVER)))
            }

            time = System.currentTimeMillis() - time
            if (time < 5000) {
                Log.i(TAG, "NON-FUNCTIONAL REQUIREMENT PASS: time to send message is $time")
            } else {
                Log.e(TAG, "NON-FUNCTIONAL REQUIREMENT FAIL: time to send message is $time")
            }
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
        time = System.currentTimeMillis()
        val socketInfo = JSONObject()
        try {
            socketInfo.put("message", message)
            socketInfo.put("username", myUsername)
            socketInfo.put("userId", myUserId)
        } catch(e: Exception) {
            Log.e(TAG, "error sending message", e)
        }
        messageSocket!!.emit(EVENT_MESSAGE, socketInfo)
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