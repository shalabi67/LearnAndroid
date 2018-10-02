package com.learn.learnmvvm.mvvm_lifedata.view_model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.learn.learnmvvm.mvvm_lifedata.database.Table
import com.learn.learnmvvm.mvvm_lifedata.models.Quote

class QuotesViewModel(private val quotesTable : Table<Quote>) : ViewModel() {
    fun addQuote(quote : Quote) = quotesTable.add(quote)
    fun getQuotes() : LiveData<List<Quote>> = quotesTable.getItems()
}