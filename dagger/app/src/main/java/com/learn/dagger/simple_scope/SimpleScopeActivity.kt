package com.learn.dagger.simple_scope

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.learn.dagger.R
import com.learn.dagger.simple_scope.configuration.DaggerSimpleScopeComponent
import com.learn.dagger.simple_scope.configuration.SimpleScopeComponent
import com.learn.dagger.simple_static.configuration.PersonModule

class SimpleScopeActivity : AppCompatActivity() {
    private lateinit var simpleScopeComponent: SimpleScopeComponent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_scope)

        simpleScopeComponent = DaggerSimpleScopeComponent.create()

        val button = findViewById<Button>(R.id.incrementButton)
        button.setOnClickListener{
            val simpleScopeFactory = SimpleScopeFactory()
            simpleScopeComponent.inject(simpleScopeFactory)

            val textView  = findViewById<TextView>(R.id.message1)
            textView.text = "scoped person = " + simpleScopeFactory.personStatic.count.toString()

            val textView1  = findViewById<TextView>(R.id.message2)
            textView1.text = "Simple person = " + simpleScopeFactory.person.count.toString()
        }
    }
}

