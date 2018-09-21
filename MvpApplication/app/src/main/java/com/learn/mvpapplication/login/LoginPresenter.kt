package com.learn.mvpapplication.login

import javax.inject.Inject

class LoginPresenter {
    var loginModel : LoginModel
    lateinit var loginView: LoginView

    @Inject
    constructor(loginModel : LoginModel) {
        this.loginModel = loginModel
    }

    fun setView(loginView : LoginView) {
        this.loginView = loginView
    }

    fun login() {
        val user = User(loginView.getUserName(), loginView.getPassword())
        if(user.isValidUser()) {
            loginModel.saveUser(user)
            loginView.close()
        } else {
            loginView.showError("invaled user")
        }
    }
}