package com.learn.mvpapplication.login

import android.app.Activity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.learn.mvpapplication.R
import javax.inject.Inject

class LoginView {
    private lateinit var userNameEditText : EditText
    private lateinit var passwordEditText : EditText
    private val loginPresenter : LoginPresenter
    private lateinit var loginActivity : Activity

    @Inject
    constructor(loginPresenter : LoginPresenter) {
        this.loginPresenter = loginPresenter
    }

    fun setActivity(loginActivity : Activity) {
        loginPresenter.setView(this)
        this.loginActivity = loginActivity
        userNameEditText = loginActivity.findViewById(R.id.userNameEditTextId)
        passwordEditText = loginActivity.findViewById(R.id.passwordEditTextId)

        val loginButton = loginActivity.findViewById<Button>(R.id.login_button_id)
        loginButton.setOnClickListener {
            Toast.makeText(loginActivity,"Login clicked", Toast.LENGTH_LONG)
            loginPresenter.login()
        }
    }

    fun showError(errorMessage : String) {
        Toast.makeText(loginActivity, errorMessage, Toast.LENGTH_SHORT)
    }

    fun close() {
        loginActivity.finish()
    }

    fun getUserName() : String {
        return userNameEditText.text.toString()
    }
    fun setUserName(userName : String) {
        userNameEditText.setText(userName)
    }
    fun getPassword() : String {
        return passwordEditText.text.toString()
    }
    fun setPassword(password : String) {
        passwordEditText.setText(password)
    }
}