package com.learn.socialmedia

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.facebook.login.widget.LoginButton
import com.twitter.sdk.android.core.identity.TwitterLoginButton
import com.learn.socialmedia.framework.FacebookLogin
import com.learn.socialmedia.framework.GoogleLogin
import com.learn.socialmedia.framework.TwitterLogin


class LoginActivity : AppCompatActivity() {
    companion object {
        val TAG = LoginActivity::class.java.name
    }

    private lateinit var facebookLogin : FacebookLogin
    private lateinit var facebookTracker : FacebookTracker

    private lateinit var twitterLogin : TwitterLogin

    private lateinit var googleLogin : GoogleLogin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        facebookLogin = FacebookLogin(findViewById<LoginButton>(R.id.login_facebook_button),
                listOf("public_profile", "email", "user_friends")) //, "user_birthday"
        facebookTracker = FacebookTracker(facebookLogin)

        //twitter login
        twitterLogin = TwitterLogin(findViewById<TwitterLoginButton>(R.id.login_button))


        googleLogin = GoogleLogin(this, R.id.google_sign_in_button)

    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(TAG, "requestCode = $requestCode")

        twitterLogin.onActivityResult(requestCode, resultCode, data)
        facebookLogin.onActivityResult(requestCode, resultCode, data)
        googleLogin.onActivityResult(requestCode, resultCode, data)

    }

    override fun onDestroy() {
        super.onDestroy()

        facebookTracker.stopTracking()
    }
}
