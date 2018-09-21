package com.learn.mvpapplication.login

import javax.inject.Inject

class User {
    var userName : String
    var password : String

    constructor(userName : String, password : String) {
        this.userName = userName.trim()
        this.password = password.trim()
    }
    fun isValidUser() : Boolean {
        if(userName.isNullOrEmpty() || password.isNullOrEmpty())
            return false
        return true
    }
}