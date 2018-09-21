package com.learn.mvpapplication.configuration

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule {
    private val application : Application

    constructor(application : Application) {
        this.application = application
    }

    @Provides
    @Singleton
    fun getContext() : Context {
        return application
    }
}