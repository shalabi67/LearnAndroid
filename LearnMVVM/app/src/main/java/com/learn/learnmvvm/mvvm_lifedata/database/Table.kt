package com.learn.learnmvvm.mvvm_lifedata.database

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData

class Table<T> {
    private val itemList = mutableListOf<T>()
    private val items = MutableLiveData<List<T>>()

    init {
        items.value = itemList
    }

    fun add(item : T) {
        itemList.add(item)
        items.value = itemList
    }

    fun getItems() = items as LiveData<List<T>>
}