package com.learn.mvpapplication.login

import javax.inject.Inject

class LoginPresenter {
    private var loginModel : LoginModel
    private lateinit var loginView: LoginView

    @Inject
    constructor(loginModel : LoginModel) {
        this.loginModel = loginModel
    }

    fun setView(loginView : LoginView) {
        this.loginView = loginView
    }

    fun login() : Boolean {
        val user = User(loginView.getUserName(), loginView.getPassword())
        if(user.isValidUser(loginModel.getLoggedUser())) {
            loginView.close()
            return true
        } else {
            loginView.showError("invalid user")
            return false
        }
    }
}