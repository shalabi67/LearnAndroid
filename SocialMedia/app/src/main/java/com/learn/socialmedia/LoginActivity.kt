package com.learn.socialmedia

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.facebook.CallbackManager
import com.facebook.FacebookSdk
import com.facebook.login.widget.LoginButton
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.FacebookCallback
import java.util.concurrent.Callable


class LoginActivity : AppCompatActivity() {
    companion object {
        val TAG = LoginActivity::class.java.name
    }

    lateinit var  facebookSignInButton : LoginButton
    lateinit var facebookCallbackManager : CallbackManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login)

        facebookCallbackManager = CallbackManager.Factory.create()
        facebookSignInButton = findViewById<LoginButton>(R.id.login_facebook_button)
        facebookSignInButton.registerCallback(facebookCallbackManager,
                object : FacebookCallback<LoginResult> {
                    override fun onSuccess(loginResult: LoginResult) {
                        //TODO: Use the Profile class to get information about the current user.
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


    }

    private fun handleSignInResult(o: Any?) {
        //Handle sign result here
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        facebookCallbackManager.onActivityResult(requestCode, resultCode, data);

    }
}
