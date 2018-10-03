package com.learn.learnmvvm.restaurant_calculator.services

import com.learn.learnmvvm.restaurant_calculator.models.Payment

object TipCalculator {
    fun calculate(payment : Payment) : Double{
        return payment.checkAmount + payment.checkAmount * payment.tipPercentage/100
    }
}