package com.learn.daggeradvanced.application

import android.app.Application
import com.learn.daggeradvanced.application.configuration.ApplicationComponent
import com.learn.daggeradvanced.application.configuration.DaggerApplicationComponent
import javax.inject.Inject

class AdvancedDaggerApplication : Application() {
    @Inject
    lateinit var applicationComponent: ApplicationComponent
    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.create()
        applicationComponent.inject(this)
    }
}