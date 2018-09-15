package com.learn.learndagger

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.learn.learndagger.configuration.DaggerMainComponent
import com.learn.learndagger.domain.Body
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var body : Body
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerMainComponent.create().inject(this)
    }
}
