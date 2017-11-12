package com.learn.services.outbound_service_in_another_process

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import android.support.design.widget.Snackbar
import android.util.Log
import android.widget.Toast

class MyMessengerService : Service() {

    val name = "MyMessengerService"
    companion object {
        val FIRST_NUMBER = "MyMessengerService.FIRST_NUMBER"
        val SECOND_NUMBER = "MyMessengerService.SECOND_NUMBER"
        val ADD_ACTION = 3
        private val TAG = MyMessengerService::class.java.name
    }
    override fun onBind(intent: Intent): IBinder? {
        Log.i(TAG, "onBind")
        return messenger.binder
    }

    fun add(a:Int, b:Int) : Int {
        return a + b
    }

    private val incomingHandler = IncomingHandler()
    val messenger = Messenger(incomingHandler)
    inner class IncomingHandler : Handler() {
        val TAG : String = IncomingHandler::class.java.name
        val name = "IncomingHandler"
        override fun handleMessage(msg: Message?) {
            Log.i(TAG, "handleMessage")

            if(msg == null) {
                Log.e(TAG, "null message received.")
                return
            }
            val bundle = msg.data
            val firstNumber = bundle.getInt(MyMessengerService.FIRST_NUMBER, 0)
            val secondNumber = bundle.getInt(MyMessengerService.SECOND_NUMBER, 0)
            var result : Int = 0

            when(msg.what) {
                MyMessengerService.ADD_ACTION -> result = add(firstNumber, secondNumber)
                else -> super.handleMessage(msg)
            }

            Log.i(TAG, "The result is $result")
        }
    }
}
