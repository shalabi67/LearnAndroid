package com.learn.mvpapplication.configuration

import android.app.Activity
import dagger.Component
import javax.inject.Singleton

@Component(modules= [ApplicationModule::class])
@Singleton
interface ApplicationComponent {
    fun inject(activity : Activity)
}