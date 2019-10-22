package com.example.myapplication.auth.syncadapter

import android.app.Service
import android.content.Intent
import android.os.IBinder

class HustlrAuthenticatorService : Service() {

    // Instance field that stores the authenticator object
    private lateinit var mAuthenticator: HustlrAuthenticator

    override fun onCreate() {
        // Create a new authenticator object
        mAuthenticator = HustlrAuthenticator(this)
    }

    /*
     * When the system binds to this Service to make the RPC call
     * return the authenticator's IBinder.
     */
    override fun onBind(intent: Intent?): IBinder = mAuthenticator.iBinder
}
