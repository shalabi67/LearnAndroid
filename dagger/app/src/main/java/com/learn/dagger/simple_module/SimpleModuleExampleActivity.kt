package com.learn.dagger.simple_module

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.learn.dagger.R
import com.learn.dagger.configuration.DaggerPersonComponent
import com.learn.dagger.simple_module.domain.Person
import javax.inject.Inject

class SimpleModuleExampleActivity : AppCompatActivity() {
    @Inject
    lateinit var person : Person
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_module_example)

        person = DaggerPersonComponent.create().getPerson()
        val textView  = findViewById<TextView>(R.id.message)
        textView.setText(person.getBody().getHead())
    }
}
