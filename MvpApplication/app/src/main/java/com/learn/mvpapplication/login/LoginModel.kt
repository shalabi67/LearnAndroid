package com.learn.mvpapplication.login

import javax.inject.Inject

class LoginModel {
    var user : User = User("", "")

    @Inject
    constructor() {

    }
    fun saveUser(user : User) {
        this.user = user
    }
}