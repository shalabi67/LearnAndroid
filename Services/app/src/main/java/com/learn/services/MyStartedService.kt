package com.learn.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyStartedService : Service() {
    companion object {
        val TAG = "MyStartedService"
        val SLEEP_TIME = "MyStartedService.SLEEP_TIME"
    }
    override fun onBind(intent: Intent): IBinder? {
        Log.i(TAG, "onBind, Thread name = ${Thread.currentThread().name}")
        return null
    }

    override fun onCreate() {
        Log.i(TAG, "onCreate, Thread name = ${Thread.currentThread().name}")
        super.onCreate()
    }

    override fun onDestroy() {
        Log.i(TAG, "onDestroy, Thread name = ${Thread.currentThread().name}")
        super.onDestroy()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i(TAG, "onStartCommand, Thread name = ${Thread.currentThread().name}")

        if(intent == null)
            return START_REDELIVER_INTENT

        val sleepTime : Long = intent.getLongExtra(MyStartedService.SLEEP_TIME, 1) as Long

        //do the thing this service should do. but this should be a fast operation
        //otherwise it will block the UI
        //thus this code should not run in this was so i commented it
        /*
        val sleepTime : Long = intent.getLongExtra(MyStartedService.SLEEP_TIME, 1) as Long
        Thread.sleep(sleepTime * 1000)
        */

        MyLongRunningTask(this).execute(sleepTime)

        return START_REDELIVER_INTENT
    }
}
