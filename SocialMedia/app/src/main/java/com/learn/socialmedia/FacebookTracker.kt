package com.learn.socialmedia

import android.util.Log
import com.facebook.AccessToken
import com.facebook.Profile
import com.learn.socialmedia.framework.FacebookLogin
import com.learn.socialmedia.framework.FacebookTrackerBase

/**
 * Created by mohammad on 11/25/2017.
 */
class FacebookTracker(val facebookLogin : FacebookLogin) : FacebookTrackerBase() {
    companion object {
        val TAG = FacebookTracker::class.java.name
    }
    override fun changeAccessToken(oldAccessToken: AccessToken?, newAccessToken: AccessToken?) {
        facebookLogin.facebookAccessToken = newAccessToken

        Log.d(TAG, "token = ${newAccessToken?.token}")
        Log.d(TAG, "userId = ${newAccessToken?.userId}")

    }

    override fun changeProfile(oldProfile: Profile?, newProfile: Profile?) {
        if(newProfile != null) {
            Log.d(TAG, "id = ${newProfile.id}")
            Log.d(TAG, "name = ${newProfile.name}")
            Log.d(TAG, "first name = ${newProfile.firstName}")
            Log.d(TAG, "last name = ${newProfile.lastName}")
            Log.d(TAG, "link url = ${newProfile.linkUri}")
        }
    }
}