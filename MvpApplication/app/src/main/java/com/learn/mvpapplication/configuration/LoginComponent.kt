package com.learn.mvpapplication.configuration

import android.app.Activity
import com.learn.mvpapplication.login.LoginView
import dagger.Component

@Component(modules = [LoginModule::class])
interface LoginComponent {
    fun getLoginView() : LoginView
}