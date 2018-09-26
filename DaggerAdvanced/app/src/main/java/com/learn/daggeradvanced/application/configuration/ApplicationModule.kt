package com.learn.daggeradvanced.application.configuration

import android.app.Application
import android.content.Context
import com.learn.daggeradvanced.application.AdvancedDaggerApplication
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {
    private val application : Application
    constructor(application : Application) {
        this.application = application
    }

    @Provides
    fun getApplicationContext() : Context {
        return application
    }
}