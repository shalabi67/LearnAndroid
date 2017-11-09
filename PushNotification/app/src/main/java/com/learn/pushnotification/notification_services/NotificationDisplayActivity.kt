package com.learn.pushnotification.notification_services

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.learn.pushnotification.R

class NotificationDisplayActivity : AppCompatActivity() {
    companion object {
        var IS_RUNNING = true
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_display)
    }

    override fun onDestroy() {
        super.onDestroy()

        IS_RUNNING = false
    }
}
