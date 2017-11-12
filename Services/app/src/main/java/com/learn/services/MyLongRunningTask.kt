package com.learn.services

import android.content.Intent
import android.os.AsyncTask
import android.util.Log
import com.learn.services.MainActivity.MyStartedServiceBroadcastReceiverConstants.Companion.OPERATION_COMPLETED


/**
 * Created by mohammad on 11/11/2017.
 */
class MyLongRunningTask(val myStartedService : MyStartedService) : AsyncTask<Long, String, String>() {
    companion object {
        val TAG = MyLongRunningTask::javaClass.name
    }

    override fun onPreExecute() {
        super.onPreExecute()
        Log.i(TAG, "onPreExecute, Thread name = ${Thread.currentThread().name}")
    }

    override fun onProgressUpdate(vararg values: String?) {
        super.onProgressUpdate(*values)
        val message = values[0]?:""
        Log.i(TAG, "onProgressUpdate, $message, Thread name = ${Thread.currentThread().name}")
    }

    override fun doInBackground(vararg params: Long?) : String {
        Log.i(TAG, "doInBackground, Thread name = ${Thread.currentThread().name}")

        val sleepTime : Long = params[0]?:1
        for(i in 1..sleepTime) {
            publishProgress("The value is $i")
            Thread.sleep( 1000)
        }

        return "The operation completed using Broadcast receiver."
    }

    override fun onPostExecute(result: String) {
        super.onPostExecute(result)

        myStartedService.stopSelf()
        Log.i(TAG, "onPostExecute, Thread name = ${Thread.currentThread().name}")

        val broadcastReceiverIntent = Intent(MainActivity.MyStartedServiceBroadcastReceiverConstants.SEND_DATA_ACTION)
        broadcastReceiverIntent.putExtra(OPERATION_COMPLETED, result)
        MainActivity.applicationContext.sendBroadcast(broadcastReceiverIntent)
    }
}
