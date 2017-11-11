package com.learn.services

import android.os.AsyncTask
import android.support.design.widget.Snackbar
import android.util.Log

/**
 * Created by mohammad on 11/11/2017.
 */
class MyLongRunningTask(val myStartedService : MyStartedService) : AsyncTask<Long, String, Unit>() {
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

    override fun doInBackground(vararg params: Long?) {
        Log.i(TAG, "doInBackground, Thread name = ${Thread.currentThread().name}")

        val sleepTime : Long = params[0]?:1
        for(i in 1..sleepTime) {
            publishProgress("The value is $i")
            Thread.sleep( 1000)
        }
    }

    override fun onPostExecute(result: Unit?) {
        super.onPostExecute(result)

        myStartedService.stopSelf()
        Log.i(TAG, "onPostExecute, Thread name = ${Thread.currentThread().name}")
    }
}
