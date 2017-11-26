package com.learn.socialmedia

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.View
import com.facebook.login.widget.LoginButton
import com.twitter.sdk.android.core.*
import com.twitter.sdk.android.core.identity.TwitterLoginButton
import com.twitter.sdk.android.core.TwitterException
import com.twitter.sdk.android.core.identity.TwitterAuthClient
import com.google.android.gms.common.SignInButton
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.api.ResultCallback
import com.google.android.gms.common.api.Status
import com.learn.socialmedia.framework.FacebookLogin
import com.learn.socialmedia.framework.TwitterLogin


class LoginActivity : AppCompatActivity() {
    companion object {
        val TAG = LoginActivity::class.java.name
    }

    lateinit var facebookLogin : FacebookLogin
    lateinit var facebookTracker : FacebookTracker

    //lateinit var twitterLoginButton : TwitterLoginButton
    lateinit var twitterLogin : TwitterLogin
    lateinit var googleSignInButton : SignInButton
    private var mGoogleApiClient: GoogleApiClient? = null

    //var twitterSession : TwitterSession? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        facebookLogin = FacebookLogin(findViewById<LoginButton>(R.id.login_facebook_button),
                listOf("public_profile", "email", "user_friends")) //, "user_birthday"
        facebookTracker = FacebookTracker(facebookLogin)

        //twitter login
        twitterLogin = TwitterLogin(findViewById<TwitterLoginButton>(R.id.login_button))


        googleSignInButton = findViewById<View>(R.id.google_sign_in_button) as SignInButton
        //findViewById(R.id.signOut)
        googleSignInButton.setOnClickListener(View.OnClickListener { v ->
            when (v.id) {
                R.id.google_sign_in_button -> signInWithGoogle()
             //   R.id.signOut -> signOutFromGoogle()
            }
        })


    }

    private fun signInWithGoogle() {
        if (mGoogleApiClient != null) {
            mGoogleApiClient?.disconnect()
        }

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken(getString(R.string.google_client_id))
                .requestProfile()
                .requestServerAuthCode(getString(R.string.google_client_id))
                .build()
        mGoogleApiClient = GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build()

        val signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient)
        startActivityForResult(signInIntent, 9001)
    }

    private fun signOutFromGoogle() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                object : ResultCallback<Status> {
                    override fun onResult(status: Status) {

                    }
                })
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(TAG, "requestCode = $requestCode")

        twitterLogin.onActivityResult(requestCode, resultCode, data)
        facebookLogin.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 9001) {
            val result : GoogleSignInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            if(result.isSuccess) {
                val client : GoogleApiClient? = mGoogleApiClient;

                val googleSignInAccount = result.signInAccount
                Log.d(TAG, "Email = ${googleSignInAccount?.email}")
                Log.d(TAG, "Token = ${googleSignInAccount?.idToken}")
                Log.d(TAG, "Display Name = ${googleSignInAccount?.displayName}")
                Log.d(TAG, "Display serverAuthCode = ${googleSignInAccount?.serverAuthCode}")
                Log.d(TAG, "givenName = ${googleSignInAccount?.givenName}")
                Log.d(TAG, "familyName = ${googleSignInAccount?.familyName}")
                Log.d(TAG, "id = ${googleSignInAccount?.id}")
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()

        facebookTracker.stopTracking()
    }
}
