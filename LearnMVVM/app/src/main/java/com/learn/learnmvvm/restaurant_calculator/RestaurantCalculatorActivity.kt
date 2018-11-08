package com.learn.learnmvvm.restaurant_calculator

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.learn.learnmvvm.R
import com.learn.learnmvvm.databinding.ActivityRestaurantCalculatorBinding
import com.learn.learnmvvm.restaurant_calculator.view_models.CalculatorViewModel

class RestaurantCalculatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_restaurant_calculator)

        val calculatorViewModel = ViewModelProviders.of(this)
                .get(CalculatorViewModel::class.java)

        DataBindingUtil.setContentView<ActivityRestaurantCalculatorBinding>(
                this, R.layout.activity_restaurant_calculator
        ).apply {
            this.setLifecycleOwner(this@RestaurantCalculatorActivity)
            this.vm = calculatorViewModel
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.activity_resturant_calculator, menu);
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

}
