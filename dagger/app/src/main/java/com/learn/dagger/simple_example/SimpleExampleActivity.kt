package com.learn.dagger.simple_example

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.learn.dagger.R
import com.learn.dagger.configuration.DaggerSimpleExampleComponent
import com.learn.dagger.simple_example.domain.Person
import javax.inject.Inject

class SimpleExampleActivity : AppCompatActivity() {
    @Inject
    lateinit var person : Person
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_example)

        DaggerSimpleExampleComponent.create().inject(this)

        val textView  = findViewById<TextView>(R.id.message)
        textView.setText(person.getBody().getHead())
    }
}
