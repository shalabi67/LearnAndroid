package com.learn.mvpapplication.configuration

import com.learn.mvpapplication.login.LoginModel
import com.learn.mvpapplication.login.LoginPresenter
import com.learn.mvpapplication.login.LoginView
import dagger.Module
import dagger.Provides

@Module
class LoginModule {
    @Provides
    fun provideLoginView(loginPresenter: LoginPresenter) : LoginView {
        return LoginView(loginPresenter)
    }

    @Provides
    fun provideLoginModel() : LoginModel {
        return LoginModel()
    }

    @Provides
    fun provideLoginPresenter(loginModel : LoginModel) : LoginPresenter {
        return LoginPresenter(loginModel)
    }
}