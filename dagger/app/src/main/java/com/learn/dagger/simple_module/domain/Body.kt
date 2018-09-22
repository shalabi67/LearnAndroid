package com.learn.dagger.simple_module.domain

import javax.inject.Inject

//no need to have @inject here since it requires no other objects just simple default constructor
class Body  constructor() {
    fun getHead(): String {
        return "this is my head from simple module"
    }
}