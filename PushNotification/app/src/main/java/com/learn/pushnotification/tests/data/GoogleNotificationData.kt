package com.learn.pushnotification.tests.data

import com.google.gson.Gson
import com.google.android.gms.tasks.Task
import com.google.gson.reflect.TypeToken



/**
 * Created by mohammad on 11/11/2017.
 */
class GoogleNotificationData(val to : String, val data: NotificationData) {
    override fun toString(): String {
        val gson = Gson()
        val jsonString = gson.toJson(this)
        return jsonString
    }

}