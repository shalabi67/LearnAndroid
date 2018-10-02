package com.learn.learnmvvm.mvvm_lifedata.database

import com.learn.learnmvvm.mvvm_lifedata.models.Quote

class Database private constructor() {
    val quotesTable = Table<Quote>()
    companion object {
        private val database = Database()

        fun create() : Database {
            return database
        }

    }
}