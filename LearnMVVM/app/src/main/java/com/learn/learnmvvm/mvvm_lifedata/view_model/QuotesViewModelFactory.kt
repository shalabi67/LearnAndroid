package com.learn.learnmvvm.mvvm_lifedata.view_model

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.learn.learnmvvm.mvvm_lifedata.database.Database
import com.learn.learnmvvm.mvvm_lifedata.database.Table
import com.learn.learnmvvm.mvvm_lifedata.models.Quote

class QuotesViewModelFactory(private val quotesTable : Table<Quote>)
    : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return QuotesViewModel(quotesTable) as T
    }

    companion object {
        fun create() : QuotesViewModelFactory {
            return QuotesViewModelFactory(Database.create().quotesTable)
        }
    }
}