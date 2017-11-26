package com.learn.socialmedia.framework

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.facebook.*
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import org.json.JSONObject
import com.facebook.AccessToken
import com.facebook.AccessTokenTracker
import com.learn.socialmedia.LoginActivity


/**
 * Created by mohammad on 11/25/2017.
 */
class FacebookLogin {
    companion object {
        val TAG = FacebookLogin::class.java.name
    }
    private var  facebookSignInButton : LoginButton
    private val facebookCallbackManager : CallbackManager = CallbackManager.Factory.create()
    var facebookAccessToken : AccessToken? = AccessToken.getCurrentAccessToken()

    constructor(facebookSignInButton : LoginButton, permissionList : List<String>) {
        //facebookCallbackManager = CallbackManager.Factory.create()
        this.facebookSignInButton = facebookSignInButton
        facebookSignInButton.setReadPermissions(permissionList)
        facebookSignInButton.registerCallback(facebookCallbackManager, FacebookLoginCallback())
    }
    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == 64206) {
            Log.d(TAG, "onActivityResult called")
            facebookCallbackManager.onActivityResult(requestCode, resultCode, data)
        }
    }
    inner class FacebookLoginCallback : FacebookCallback<LoginResult> {
        override fun onSuccess(loginResult: LoginResult) {
            Log.d(LoginActivity.TAG, "Facebook oauth success")
            facebookAccessToken = loginResult.accessToken
            Log.d(LoginActivity.TAG, "Facebook access token = ${facebookAccessToken.toString()}")
            Log.d(LoginActivity.TAG, "User Id = ${loginResult.accessToken.userId}")
            Log.d(LoginActivity.TAG, "Token = ${loginResult.accessToken.token}")
            /*
            val profile = Profile.getCurrentProfile()
            Log.d(TAG, "firstName = ${profile.firstName}")
            Log.d(TAG, "lastName = ${profile.lastName}")
            Log.d(TAG, "name = ${profile.name}")
            */

            val request : GraphRequest = GraphRequest.newMeRequest(
                    loginResult.accessToken,
                    GraphRequest.GraphJSONObjectCallback() { jsonObject: JSONObject, graphResponse: GraphResponse ->
                        val email = jsonObject.getString("email")
                        val birthday = jsonObject.getString("birthday")
                        val id = jsonObject.getString("id")
                        val gender = jsonObject.getString("gender")
                        val name = jsonObject.getString("name")
                        val first_name = jsonObject.getString("first_name")
                        val last_name = jsonObject.getString("last_name")
                        val firends = jsonObject.getJSONObject("friends").getJSONArray("data")

                        Log.d(LoginActivity.TAG, "email = $email")
                        Log.d(LoginActivity.TAG, "birthday = $birthday")
                        Log.d(LoginActivity.TAG, "id = $id")
                        Log.d(LoginActivity.TAG, "gender = $gender")
                        Log.d(LoginActivity.TAG, "name = $name")
                        Log.d(LoginActivity.TAG, "first_name = $first_name")
                        Log.d(LoginActivity.TAG, "last_name = $last_name")
                    })
            val parameters : Bundle = Bundle()
            parameters.putString("fields", "id,name,email,gender,birthday,last_name,first_name,friends")
            request.parameters = parameters
            request.executeAsync()
        }

        override fun onError(error: FacebookException?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onCancel() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }
}