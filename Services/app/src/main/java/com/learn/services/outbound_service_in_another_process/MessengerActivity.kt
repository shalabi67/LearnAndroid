package com.learn.services.outbound_service_in_another_process

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import com.learn.services.R
import com.learn.services.bind_service.MyBoundService

class MessengerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_messenger)
    }

    fun performAddOperation(view : View) {
        val firstNumber = getIntValueFromView(R.id.etNumOne)
        val secondNumber = getIntValueFromView(R.id.etNumTwo)
        var result : Int = 0

        if(isBound) {
            //do operation
            val messageToService = createMessage(firstNumber, secondNumber)
            outboundService.send(messageToService)
        }



    }

    private fun createMessage(firstNumber:Int, secondNumber:Int) :Message {
        val messageToService  = Message.obtain(null, MyMessengerService.ADD_ACTION)

        val bundle = Bundle()
        bundle.putInt(MyMessengerService.FIRST_NUMBER, firstNumber)
        bundle.putInt(MyMessengerService.SECOND_NUMBER, secondNumber)

        messageToService.data = bundle
        messageToService.replyTo = incomingMessenger  //this will be used by service to send data back

        return messageToService
    }
    fun bindService(view : View) {
        val intent = Intent(this, MyMessengerService::class.java)
        bindService(intent, myOutboundServiceConnection, Context.BIND_AUTO_CREATE)
    }
    fun unbindService(view : View) {
        if(isBound) {
            unbindService(myOutboundServiceConnection)
            isBound = false
        }
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

    var isBound = false
    val myOutboundServiceConnection = MyOutboundServiceConnection()
    lateinit var outboundService : Messenger
    inner class MyOutboundServiceConnection : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            isBound = false
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            isBound = true
            if(service == null) {
                return
            }

            outboundService = Messenger(service)

        }

    }


    //code for receiving data back from service
    val incomingMessenger = Messenger(IncomingResponseHandler())
    inner class IncomingResponseHandler : Handler() {
        override fun handleMessage(msg: Message?) {

            if(msg == null)
                return
            val bundle = msg.data
            val result = bundle.getInt(MyMessengerService.RESULT, 0)
            when(msg.what) {
                MyMessengerService.RESULT_ACTION -> {
                    setIntValueFromView(R.id.txvResult, result)
                }
                else -> super.handleMessage(msg)
            }
        }
    }
}
