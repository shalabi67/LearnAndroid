package com.learn.socialmedia.framework

import android.app.Activity
import android.content.Intent
import android.support.v4.app.FragmentActivity
import android.util.Log
import android.view.View
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.ResultCallback
import com.google.android.gms.common.api.Status
import com.learn.socialmedia.LoginActivity
import com.learn.socialmedia.R

/**
 * Created by mohammad on 11/26/2017.
 */
class GoogleLogin {
    companion object {
        val TAG = GoogleLogin::class.java.name
        val REQIEST_CODE = 9001
    }
    private var mGoogleApiClient: GoogleApiClient? = null
    val googleSignInButton: SignInButton
    val activity : Activity
    constructor(activity : Activity, signInButtonId : Int) {
        this.activity = activity
        this.googleSignInButton = activity.findViewById<View>(signInButtonId) as SignInButton
        this.googleSignInButton.setOnClickListener(View.OnClickListener { v ->
            when (v.id) {
                R.id.google_sign_in_button -> signInWithGoogle()
            //   R.id.signOut -> signOutFromGoogle()
            }
        })
    }
    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQIEST_CODE) {
            val result : GoogleSignInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            if(result.isSuccess) {
                val client : GoogleApiClient? = mGoogleApiClient

                val googleSignInAccount = result.signInAccount
                Log.d(LoginActivity.TAG, "Email = ${googleSignInAccount?.email}")
                Log.d(LoginActivity.TAG, "Token = ${googleSignInAccount?.idToken}")
                Log.d(LoginActivity.TAG, "Display Name = ${googleSignInAccount?.displayName}")
                Log.d(LoginActivity.TAG, "Display serverAuthCode = ${googleSignInAccount?.serverAuthCode}")
                Log.d(LoginActivity.TAG, "givenName = ${googleSignInAccount?.givenName}")
                Log.d(LoginActivity.TAG, "familyName = ${googleSignInAccount?.familyName}")
                Log.d(LoginActivity.TAG, "id = ${googleSignInAccount?.id}")
            }
        }
    }
    private fun signInWithGoogle() {
        if (mGoogleApiClient != null) {
            mGoogleApiClient?.disconnect()
        }

        val context = googleSignInButton.context.applicationContext
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken(context.getString(R.string.google_client_id))
                .requestProfile()
                .requestServerAuthCode(context.getString(R.string.google_client_id))
                .build()
        mGoogleApiClient = GoogleApiClient.Builder(context)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build()

        val signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient)
        activity.startActivityForResult(signInIntent, REQIEST_CODE)
    }

    private fun signOutFromGoogle() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                object : ResultCallback<Status> {
                    override fun onResult(status: Status) {

                    }
                })
    }
}