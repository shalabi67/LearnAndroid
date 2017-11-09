package com.learn.pushnotification.notification_services

import android.app.IntentService
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class NotificationService : IntentService("NotificationService") {
    companion object {
        val TAG = javaClass.simpleName
    }
    override fun onHandleIntent(p0: Intent?) {
        Log.d(TAG, "Notification service had been called by fav button or by click on notfication message.")
    }

}
