package com.learn.dagger.simple_example.domain

import javax.inject.Inject

class Body @Inject constructor() {
    fun getHead(): String {
        return "this is my head"
    }
}