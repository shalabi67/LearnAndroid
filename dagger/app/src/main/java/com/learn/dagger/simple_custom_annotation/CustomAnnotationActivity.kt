package com.learn.dagger.simple_custom_annotation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.learn.dagger.R
import com.learn.dagger.simple_custom_annotation.configuration.Choose
import com.learn.dagger.simple_custom_annotation.configuration.DaggerCustomAnnotationComponent
import com.learn.dagger.simple_qualifier.configuration.HTTP
import com.learn.dagger.simple_qualifier.configuration.TCP
import com.learn.dagger.simple_qualifier.domain.Communication
import javax.inject.Inject

class CustomAnnotationActivity : AppCompatActivity() {
    @Inject
    @field:Choose(TCP)
    lateinit var tcpCommuinication : Communication

    @Inject
    @field:Choose(HTTP)
    lateinit var httpCommuinication : Communication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_annotation)

        DaggerCustomAnnotationComponent.create().inject(this)
        val textView  = findViewById<TextView>(R.id.message1)
        textView.setText(httpCommuinication.getProtocol())

        val textView1  = findViewById<TextView>(R.id.message2)
        textView1.setText(tcpCommuinication.getProtocol())
    }
}
