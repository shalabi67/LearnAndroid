package com.learn.learndagger.domain

import javax.inject.Inject

class Body @Inject constructor() {
    fun getHead(): String {
        return "this is my head"
    }
}