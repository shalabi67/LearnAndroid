package com.learn.androidreactiveprogramming

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NetworkingActivity : AppCompatActivity() {

    val publishSubject =  NetworkService().createPublisher()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_networking)

        getServiceData()
    }

    private fun getServiceData() {
        NetworkService().getData()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map { i->
                    Thread.sleep(100)
                    i*10
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{ i -> Log.i("NETWORK", i.toString())}
    }
}
