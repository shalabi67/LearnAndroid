package com.learn.dagger.simple_subcomponent

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.learn.dagger.R
import com.learn.dagger.simple_subcomponent.configuration.DaggerOuterComponent
import com.learn.dagger.simple_subcomponent.configuration.InnerScopeComponent
import com.learn.dagger.simple_subcomponent.configuration.OuterComponent
import com.learn.dagger.simple_subcomponent.factory.InnerScopeFactory

class SubcomponentActivity : AppCompatActivity() {

    private lateinit var outerComponent: OuterComponent;
    private lateinit var innerScopeComponent: InnerScopeComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subcomponent)

        outerComponent = DaggerOuterComponent.create()
        innerScopeComponent = outerComponent.getInnerComponent()

        val outerButton = findViewById<Button>(R.id.mainButton)
        outerButton.setOnClickListener {
            innerScopeComponent = outerComponent.getInnerComponent()
            showData()
        }

        val innerButton = findViewById<Button>(R.id.subcomponentButton)
        innerButton.setOnClickListener {
            showData()
        }
    }

    private fun showData() {
        val innerScopeFactory = InnerScopeFactory()
        innerScopeComponent.inject(innerScopeFactory)
        val textView  = findViewById<TextView>(R.id.message1)
        textView.text = "static person = " + innerScopeFactory.personOuter.count.toString()

        val textView1  = findViewById<TextView>(R.id.message2)
        textView1.text = "static for subcomponent  person = " + innerScopeFactory.personInner.count.toString()

        val textView2  = findViewById<TextView>(R.id.message3)
        textView2.text = "Simple person = " + innerScopeFactory.person.count.toString()
    }
}
