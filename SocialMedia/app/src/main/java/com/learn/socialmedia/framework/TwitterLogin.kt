package com.learn.socialmedia.framework

import android.content.Intent
import android.support.design.widget.Snackbar
import android.util.Log
import com.learn.socialmedia.LoginActivity
import com.twitter.sdk.android.core.*
import com.twitter.sdk.android.core.identity.TwitterAuthClient
import com.twitter.sdk.android.core.identity.TwitterLoginButton

/**
 * Created by mohammad on 11/26/2017.
 */
class TwitterLogin {
    companion object {
        val TAG = TwitterLogin::class.java.name
    }
    var twitterSession : TwitterSession?
    private val twitterLoginButton : TwitterLoginButton

    constructor(twitterLoginButton : TwitterLoginButton) {
        twitterLoginButton.callback = TwittwerLoginCallback()
        this.twitterLoginButton = twitterLoginButton

        twitterSession = TwitterCore.getInstance().sessionManager.activeSession
        logDebugInfo()
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == 140) {
            Log.d(TAG, "onActivityResult called")
            twitterLoginButton.onActivityResult(requestCode, resultCode, data)
        }
    }

    fun onSuccess() {
        //get email
        getTwitterEmail()
    }
    fun onFiled(exception: TwitterException) {
        Snackbar.make(twitterLoginButton, "Twitter login failed", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
    }
    inner class TwittwerLoginCallback : Callback<TwitterSession>() {
        override fun success(twitterSession: Result<TwitterSession>) {
            val userName = twitterSession.data.userName
            val userId = twitterSession.data.userId
            this@TwitterLogin.twitterSession = TwitterCore.getInstance().sessionManager.activeSession
            val session = this@TwitterLogin.twitterSession ?:return
            val authToken = session.authToken
            val token = authToken.token
            val secret = authToken.secret

            logDebugInfo()

            onSuccess()
        }

        override fun failure(exception: TwitterException) {
            Log.e(LoginActivity.TAG, "Twitter login failed.")
            Log.e(LoginActivity.TAG, exception.message)

            onFiled(exception)
        }


    }

    var userEmail  = ""
    private fun getTwitterEmail()  {
        val session = twitterSession?:return
        val authClient = TwitterAuthClient()
        authClient.requestEmail(session, object : Callback<String>() {
            override fun success(result: Result<String>) {
                val email = result.data?:return
                Log.d(LoginActivity.TAG, "twitter email = $email")
                userEmail = email
            }

            override fun failure(exception: TwitterException) {
                userEmail = ""
            }
        })
    }

    private fun logDebugInfo() {
        val session = twitterSession ?:return
        Log.d(LoginActivity.TAG, "Token = ${session.authToken.token}")
        Log.d(LoginActivity.TAG, "Secret = ${session.authToken.secret}")
        Log.d(LoginActivity.TAG, "User name = ${session.userName}")
        Log.d(LoginActivity.TAG, "User id = ${session.userId}")
    }
}