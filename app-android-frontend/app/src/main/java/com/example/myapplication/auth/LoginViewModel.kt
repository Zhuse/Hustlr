package com.example.myapplication.auth

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.*
import com.example.myapplication.R
import com.example.myapplication.hustles.HustlesActivity
import net.openid.appauth.*

class LoginViewModel: ViewModel() {
    val navigation: MutableLiveData<Pair<Int, Intent>> = MutableLiveData()
    val message: MutableLiveData<Pair<Int, Int>> = MutableLiveData()

    private var authService: AuthorizationService? = null

    override fun onCleared() {
        super.onCleared()

        authService = null
    }

    fun sendGoogleAuthRequest(context: Context) {
        val serviceConfig = AuthorizationServiceConfiguration(
            Uri.parse("https://accounts.google.com/o/oauth2/v2/auth"),
            Uri.parse("https://oauth2.googleapis.com/token")
        )
        val authRequestBuilder = AuthorizationRequest.Builder(
            serviceConfig,
            GOOGLE_CID,
            ResponseTypeValues.CODE,
            Uri.parse("myapplication.example.com:/auth"))
        val authRequest = authRequestBuilder
            .setScope("openid email profile")
            .build()
        authService = AuthorizationService(context)
        val authIntent = authService!!.getAuthorizationRequestIntent(authRequest)
        navigation.postValue(Pair(RC_AUTH, authIntent))
    }

    fun sendGoogleTokenRequest(context: Context, data: Intent) {
        val authResp = AuthorizationResponse.fromIntent(data)
        val authEx = AuthorizationException.fromIntent(data)

        if (authResp != null) {
            authService!!.performTokenRequest(authResp.createTokenExchangeRequest()) { tokenResp, tokenEx ->
                if (tokenResp != null) {
                    val intent = Intent(context, HustlesActivity::class.java)
                    // TODO: backend call + create account?
                    navigation.postValue(Pair(START_NO_RESULT, intent))
                } else {
                    Log.w(TAG, "Login Failed", tokenEx)
                    message.postValue(Pair(MSG_TOAST, R.string.login_fail))
                }
            }
        } else {
            Log.w(TAG, "Login Failed", authEx)
            message.postValue(Pair(MSG_TOAST, R.string.login_fail))
        }
    }

    companion object {
        const val TAG = "LoginViewModel"
    }
}
