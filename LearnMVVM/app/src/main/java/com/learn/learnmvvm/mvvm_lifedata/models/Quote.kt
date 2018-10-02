package com.learn.learnmvvm.mvvm_lifedata.models

data class Quote(val quoteText: String, val author:String) {
    override fun toString(): String {
        return "'$quoteText'-'$author'"
    }
}