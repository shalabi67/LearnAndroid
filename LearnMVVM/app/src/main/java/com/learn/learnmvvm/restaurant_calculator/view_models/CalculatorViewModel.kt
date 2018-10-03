package com.learn.learnmvvm.restaurant_calculator.view_models

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.Bindable
import com.learn.learnmvvm.restaurant_calculator.models.Payment
import com.learn.learnmvvm.restaurant_calculator.services.TipCalculator

class CalculatorViewModel : ViewModel() {
    @Bindable
    val inputCheckAmount = MutableLiveData<String>()
    @Bindable
    val inputTipPercentage = MutableLiveData<String>()

    val outputCheckAmount : LiveData<String>
        get() = inputCheckAmount

    val outputTipAmount : LiveData<String>
        get() = inputTipPercentage

    private val _outputTotalDollarAmount = MutableLiveData<String>()
    val outputTotalDollarAmount : LiveData<String>
        get() = _outputTotalDollarAmount

    val locationName : LiveData<String>
        get() = inputCheckAmount

    fun calculate() {
        val payment = Payment(inputCheckAmount.value!!.toDouble(), inputTipPercentage.value!!.toInt())
        _outputTotalDollarAmount.value = TipCalculator.calculate(payment).toString()
    }
}