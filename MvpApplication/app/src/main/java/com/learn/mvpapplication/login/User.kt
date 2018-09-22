package com.learn.mvpapplication.login

class User {
    var userName : String
    var password : String

    constructor(userName : String, password : String) {
        this.userName = userName.trim()
        this.password = password.trim()
    }
    fun isValidUser(loggedUser: User): Boolean {
        if(userName.isEmpty() || password.isEmpty()) {
            return false
        }

        return userName == loggedUser.userName && password == loggedUser.password
    }
}