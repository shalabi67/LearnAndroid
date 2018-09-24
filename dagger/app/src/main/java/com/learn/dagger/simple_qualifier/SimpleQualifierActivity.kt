package com.learn.dagger.simple_qualifier

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.learn.dagger.R
import com.learn.dagger.simple_qualifier.configuration.DaggerQualifierComponent
import com.learn.dagger.simple_qualifier.configuration.HTTP
import com.learn.dagger.simple_qualifier.configuration.TCP
import com.learn.dagger.simple_qualifier.domain.Communication
import javax.inject.Inject
import javax.inject.Named

class SimpleQualifierActivity : AppCompatActivity() {

    @Inject
    @field:Named(TCP)
    lateinit var tcpCommuinication : Communication

    @Inject
    @field:Named(HTTP)
    lateinit var httpCommuinication : Communication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_qualifier)

        DaggerQualifierComponent.create().inject(this)
        val textView  = findViewById<TextView>(R.id.message1)
        textView.setText(httpCommuinication.getProtocol())

        val textView1  = findViewById<TextView>(R.id.message2)
        textView1.setText(tcpCommuinication.getProtocol())
    }
}
