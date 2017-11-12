package com.learn.services.bind_service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

/**
 * Created by mohammad on 11/12/2017.
 */
class MyBoundService : Service() {
    val myLocalBinder = MyLocalBinder()
    override fun onBind(intent: Intent?): IBinder {
        return myLocalBinder
    }


    fun add(a:Int, b:Int) : Int {
        return a + b
    }
    fun subtract(a:Int, b:Int) : Int {
        return a - b
    }
    fun mul(a:Int, b:Int) : Int {
        return a * b
    }
    fun div(a:Int, b:Int) : Int {
        return a / b
    }

    inner class MyLocalBinder : Binder() {
        fun getService() : MyBoundService {
            return this@MyBoundService
        }
    }

}