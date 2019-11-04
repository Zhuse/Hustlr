package com.example.myapplication.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.myapplication.*
import com.example.myapplication.R
import com.google.android.gms.common.SignInButton
import kotlinx.android.synthetic.main.activity_login.*

/**
 * LoginActivity of application.
 * Manages UI and user interaction with login screen.
 */
class LoginActivity: AppCompatActivity() {

    private lateinit var vm: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initializeViewModel()
    }

    override fun onStart() {
        super.onStart()

        val signInButton: SignInButton = btn_google_login
        signInButton.setSize(SignInButton.SIZE_WIDE)
        signInButton.setOnClickListener { vm.sendGoogleAuthRequest() }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            RC_AUTH -> { vm.sendGoogleTokenRequest(data!!) }
        }
    }

    private fun initializeViewModel() {
        vm = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        vm.navigation.observe(this, Observer {
            it?.let {
                if (it.first == START_NO_RESULT) {
                    startActivity(it.second)
                } else {
                    startActivityForResult(it.second, it.first)
                }
            }
        })
        vm.message.observe(this, Observer {
            it?.let {
                when (it.first) {
                    MSG_TOAST -> {
                        Toast.makeText(this, it.second, Toast.LENGTH_SHORT).show()
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

    companion object {
        const val TAG = "LoginActivity"
    }
}
