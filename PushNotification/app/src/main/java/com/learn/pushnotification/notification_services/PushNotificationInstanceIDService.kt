package com.learn.pushnotification.notification_services

import android.preference.PreferenceManager
import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService


/**
 * Created by mohammad on 11/8/2017.
 */
class PushNotificationInstanceIDService : FirebaseInstanceIdService() {
    companion object {
        val TAG = javaClass.simpleName
        val FIREBASE_TOKEN = "NOTIFICATION_TOKEN"
    }
    override fun onTokenRefresh() {
        super.onTokenRefresh()

        val refreshedToken = FirebaseInstanceId.getInstance().token
        Log.d(TAG, "Refreshed token: $refreshedToken")

        val preferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        preferences.edit().putString(FIREBASE_TOKEN, refreshedToken).apply()
    }
}