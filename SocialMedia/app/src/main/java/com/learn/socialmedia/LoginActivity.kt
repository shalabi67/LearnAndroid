package com.learn.socialmedia

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.View
import com.facebook.login.widget.LoginButton
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.twitter.sdk.android.core.*
import java.util.concurrent.Callable
import com.twitter.sdk.android.core.identity.TwitterLoginButton
import com.twitter.sdk.android.core.TwitterException
import com.twitter.sdk.android.core.identity.TwitterAuthClient
import android.R.attr.data
import com.facebook.*


class LoginActivity : AppCompatActivity() {
    companion object {
        val TAG = LoginActivity::class.java.name
    }

    lateinit var  facebookSignInButton : LoginButton
    lateinit var facebookCallbackManager : CallbackManager
    lateinit var twitterLoginButton : TwitterLoginButton
    var twitterSession : TwitterSession? = null
    var facebookAccessToken : AccessToken? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login)

        facebookCallbackManager = CallbackManager.Factory.create()
        facebookSignInButton = findViewById<LoginButton>(R.id.login_facebook_button)
        facebookSignInButton.registerCallback(facebookCallbackManager,
                object : FacebookCallback<LoginResult> {
                    override fun onSuccess(loginResult: LoginResult) {
                        Log.d(TAG, "Facebook oauth success")
                        facebookAccessToken = loginResult.accessToken
                        handleSignInResult(object : Callable<Void> {
                            @Throws(Exception::class)
                            override fun call(): Void? {
                                LoginManager.getInstance().logOut()
                                return null
                            }
                        })
                    }

                    override fun onCancel() {
                        handleSignInResult(null)
                    }

                    override fun onError(error: FacebookException) {
                        Log.d(TAG, error.message)
                        handleSignInResult(null)
                    }
                }

        )

        twitterLoginButton = findViewById<TwitterLoginButton>(R.id.login_button)
        twitterLoginButton.callback = object : Callback<TwitterSession>() {
            override fun success(result: Result<TwitterSession>) {
                val userName = result.data.userName
                twitterSession = TwitterCore.getInstance().sessionManager.activeSession
                val session = twitterSession?:return
                val authToken = session.authToken
                val token = authToken.token
                val secret = authToken.secret
                Log.d(TAG, "Token = $token")
                Log.d(TAG, "Secret = $secret")
                Log.d(TAG, "User name = $userName")

                //get email
                getTwitterEmail()
            }

            override fun failure(exception: TwitterException) {
                Log.e(TAG, "Twitter login failed.")
                Log.e(TAG, exception.message)

                Snackbar.make(twitterLoginButton, "Twitter login failed", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
            }
        }


    }
    var userEmail  = ""
    private fun getTwitterEmail()  {
        val session = twitterSession?:return
        val authClient = TwitterAuthClient()
        authClient.requestEmail(session, object : Callback<String>() {
            override fun success(result: Result<String>) {
                val email = result.data?:return
                Log.d(TAG, email)
                userEmail = email
            }

            override fun failure(exception: TwitterException) {
                userEmail = ""
            }
        })
    }

    private fun handleSignInResult(o: Any?) {
        Log.d(TAG, "handleSignInResult")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 140) {
            twitterLoginButton.onActivityResult(requestCode, resultCode, data);
        } else {
            facebookCallbackManager.onActivityResult(requestCode, resultCode, data);
        }

    }
}
