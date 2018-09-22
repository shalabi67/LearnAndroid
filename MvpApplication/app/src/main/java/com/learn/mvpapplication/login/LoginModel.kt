package com.learn.mvpapplication.login

class LoginModel {
    companion object {
        private var validUser: User = User("moh", "sha")
    }

    fun getLoggedUser() : User {
        return validUser
    }
}