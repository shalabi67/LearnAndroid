package com.learn.mvpapplication

import android.app.Application
import com.learn.mvpapplication.configuration.ApplicationComponent
import com.learn.mvpapplication.configuration.DaggerApplicationComponent

class MvpApplication : Application() {
    private lateinit var applicationComponent : ApplicationComponent
    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.create()
    }

    fun getApplicationComponent() : ApplicationComponent {
        return applicationComponent
    }
}