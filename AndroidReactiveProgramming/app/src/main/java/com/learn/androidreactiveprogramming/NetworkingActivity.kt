package com.learn.androidreactiveprogramming

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class NetworkingActivity : AppCompatActivity() {
    val disposable = CompositeDisposable()

    val netwrokService =  NetworkService()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_networking)

        getServiceData(1)
        getServiceData(9)
        getServiceData(5)
    }

    private fun getServiceData(start : Int) {
        disposable.add(
            NetworkService().getData(start)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map { i->
                    Thread.sleep(100)
                    i*10
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{ i -> Log.i("NETWORK " + start, i.toString())
                }
        )
    }

    override fun onDestroy() {
        super.onDestroy()

        if(!disposable.isDisposed)
            disposable.dispose()
    }
}
