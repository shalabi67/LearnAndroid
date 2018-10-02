package com.learn.learnmvvm.databinding_example

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.learn.learnmvvm.R
import com.learn.learnmvvm.databinding.ActivityDatabindingBinding
import com.learn.learnmvvm.databinding_example.view_model.FruitViewModel

class DatabindingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mainViewModel = ViewModelProviders.of(this)
                .get(FruitViewModel::class.java)

        DataBindingUtil.setContentView<ActivityDatabindingBinding>(
                this, R.layout.activity_databinding
        ).apply {
            this.setLifecycleOwner(this@DatabindingActivity)
            this.viewmodel = mainViewModel
        }

        mainViewModel.editTextContent.observe(this, Observer {message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        })

    }
}

