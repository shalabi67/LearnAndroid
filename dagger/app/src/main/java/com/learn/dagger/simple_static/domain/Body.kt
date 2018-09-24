package com.learn.dagger.simple_static.domain

import javax.inject.Inject
import javax.inject.Singleton


//no need to have @inject here since it requires no other objects just simple default constructor
class Body  constructor() {
    fun getHead(): String {
        return "this is my head from simple module" + count
    }

    companion object {
        var count = 0
    }
}