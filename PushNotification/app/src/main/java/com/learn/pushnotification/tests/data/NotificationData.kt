package com.learn.pushnotification.tests.data

import com.google.gson.Gson

/**
 * Created by mohammad on 11/11/2017.
 */
open class NotificationData {
    override fun toString(): String {
        val gson = Gson()
        val jsonString = gson.toJson(this)
        return jsonString
    }
}