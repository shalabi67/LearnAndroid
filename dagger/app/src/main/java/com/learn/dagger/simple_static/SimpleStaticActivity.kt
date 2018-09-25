package com.learn.dagger.simple_static

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.learn.dagger.R
import com.learn.dagger.simple_static.configuration.DaggerPersonComponent
import com.learn.dagger.simple_static.configuration.PersonModule
import com.learn.dagger.simple_static.domain.Person

import javax.inject.Inject

class SimpleStaticActivity : AppCompatActivity() {
    @Inject
    lateinit var person : Person

    @Inject
    lateinit var person1 : Person
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_static)

        person = PersonModule.personComponent.getPerson()
        val textView  = findViewById<TextView>(R.id.message1)
        textView.setText(person.getBody().getHead())

        person1 = PersonModule.personComponent.getPerson()
        val textView1  = findViewById<TextView>(R.id.message2)
        textView1.setText(person1.getBody().getHead())
    }
}
