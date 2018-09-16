package com.learn.learndagger

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.learn.learndagger.configuration.DaggerMainComponent
import com.learn.learndagger.domain.Person
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var person : Person

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerMainComponent.create().inject(this)

        val textView  = findViewById<TextView>(R.id.message)
        textView.setText(person.getBody().getHead())
    }
}
