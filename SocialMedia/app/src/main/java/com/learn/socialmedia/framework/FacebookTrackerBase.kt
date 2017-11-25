package com.learn.socialmedia.framework

import android.util.Log
import com.facebook.AccessToken
import com.facebook.AccessTokenTracker
import com.facebook.Profile
import com.facebook.ProfileTracker

/**
 * Created by mohammad on 11/25/2017.
 */
abstract class FacebookTrackerBase {
    companion object {
        val TAG = FacebookTrackerBase::class.java.name
    }
    protected  var accessTokenTracker : AccessTokenTracker
    protected var profileTracker : ProfileTracker
    constructor() {
        accessTokenTracker = object : AccessTokenTracker() {
            override fun onCurrentAccessTokenChanged(oldAccessToken: AccessToken?,
                                                     currentAccessToken: AccessToken?) {
                Log.d(TAG, "onCurrentProfileChanged")
                changeAccessToken(oldAccessToken, currentAccessToken)
            }
        }
        accessTokenTracker.startTracking()

        profileTracker = object : ProfileTracker() {
            override fun onCurrentProfileChanged(oldProfile : Profile?, currentProfile : Profile?) {
                Log.d(TAG, "onCurrentProfileChanged")
                changeProfile(oldProfile, currentProfile)
            }
        }
        profileTracker.startTracking()
    }
    abstract fun changeAccessToken(oldAccessToken: AccessToken?, newAccessToken: AccessToken?)
    abstract fun changeProfile(oldProfile : Profile?, newProfile : Profile?)

    fun startTracking() {
        accessTokenTracker.startTracking()
        profileTracker.startTracking()
    }
    fun stopTracking() {
        accessTokenTracker.startTracking()
        profileTracker.stopTracking()
    }
}