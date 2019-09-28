package com.example.myapplication.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.google.android.gms.common.SignInButton
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val signInButton: SignInButton = btn_google_login
        signInButton.setSize(SignInButton.SIZE_WIDE)
        signInButton.setOnClickListener {
            // TODO: add logic
        }
    }
}
