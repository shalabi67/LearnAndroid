package com.learn.services.intent_service

import android.app.IntentService
import android.content.Intent
import android.content.Context
import android.util.Log
import com.learn.services.MyStartedService

class MyIntentService : IntentService("MyIntentServiceWorkerThread") {
    companion object {
        val TAG = MyIntentService::class.java.name
    }
    override fun onHandleIntent(intent: Intent?) {
        Log.i(MyStartedService.TAG, "onHandleIntent, Thread name = ${Thread.currentThread().name}")
        if (intent != null) {
            val sleepTime : Long = intent.getLongExtra(MyStartedService.SLEEP_TIME, 1) as Long
            for(i in 1..sleepTime) {
                Log.i(TAG, "The value is $i, Thread name = ${Thread.currentThread().name}")
                Thread.sleep( 1000)
            }
        }
    }


    //no need to override this method just I am doing it here as example to get more detailes on what is going on
    override fun onCreate() {
        super.onCreate()
        Log.i(MyStartedService.TAG, "onCreate, Thread name = ${Thread.currentThread().name}")
    }

    //no need to override this method just I am doing it here as example to get more detailes on what is going on
    override fun onDestroy() {
        super.onDestroy()
        Log.i(MyStartedService.TAG, "onDestroy, Thread name = ${Thread.currentThread().name}")
    }
}
