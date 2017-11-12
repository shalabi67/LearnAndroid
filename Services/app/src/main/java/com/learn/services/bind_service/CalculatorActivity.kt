package com.learn.services.bind_service

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.learn.services.R
import kotlinx.android.synthetic.main.activity_second.*
import kotlinx.android.synthetic.main.nav_header_main.view.*

class CalculatorActivity : AppCompatActivity() {
    companion object {
        val TAG = CalculatorActivity::class.java.name
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)
    }

    fun onClickEvent(view : View) {
        Log.i(TAG, "Buton ${(view as Button).text} had been clicked")
        val firstNumber = getIntValueFromView(R.id.etNumOne)
        val secondNumber = getIntValueFromView(R.id.etNumTwo)
        var result : Int = 0

        when(view.id) {
            R.id.btnAdd -> result = myBoundService.add(firstNumber, secondNumber)
            R.id.btnDiv -> result = myBoundService.div(firstNumber, secondNumber)
            R.id.btnMul -> result = myBoundService.mul(firstNumber, secondNumber)
            R.id.btnSub -> result = myBoundService.subtract(firstNumber, secondNumber)
        }

        setIntValueFromView(R.id.txvResult, result)
    }
    private fun getIntValueFromView(textViewId : Int) : Int {
        val textView = findViewById<TextView>(textViewId)
        return textView.text.toString().toInt()
    }

    private fun setIntValueFromView(textViewId : Int, value:Int)  {
        val textView = findViewById<TextView>(textViewId)
        val result = value.toString()
        textView.text = result
    }

    override fun onStart() {
        super.onStart()

        val intent = Intent(this, MyBoundService::class.java)
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onStop() {
        super.onStop()
        if(isBound) {
            unbindService(serviceConnection)
            isBound = false
        }
    }

    lateinit var myBoundService : MyBoundService
    val serviceConnection = MyServiceConnection()
    var isBound = false
    inner class MyServiceConnection : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            isBound = true
        }

        override fun onServiceConnected(name: ComponentName?, myLocalBinderParam: IBinder?) {
            isBound = false
            if(myLocalBinderParam == null) {
                return
            }
            val myLocalBinder : MyBoundService.MyLocalBinder = myLocalBinderParam as MyBoundService.MyLocalBinder
            myBoundService = myLocalBinder.getService()
        }

    }
}
