package com.learn.mvpapplication.login

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.learn.mvpapplication.R
import com.learn.mvpapplication.configuration.DaggerApplicationComponent
import com.learn.mvpapplication.configuration.DaggerLoginComponent
import com.learn.mvpapplication.configuration.LoginModule
import kotlinx.android.synthetic.main.app_bar_main.*
import javax.inject.Inject


class LoginActivity : AppCompatActivity() {

    @Inject
    lateinit var loginView : LoginView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setSupportActionBar(toolbar)

        loginView = DaggerLoginComponent.create().getLoginView()

    }

    override fun onResume() {
        super.onResume()

        loginView.setActivity(this)
    }
}
