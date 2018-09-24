package com.learn.dagger.simple_qualifier.configuration

import android.app.Activity
import com.learn.dagger.simple_qualifier.SimpleQualifierActivity
import com.learn.dagger.simple_qualifier.domain.Communication
import dagger.Component

@Component(modules = [QualifierModule::class])
interface QualifierComponent {
    fun inject(activity : SimpleQualifierActivity)
}