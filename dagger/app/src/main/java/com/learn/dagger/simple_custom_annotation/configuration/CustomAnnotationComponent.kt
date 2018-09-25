package com.learn.dagger.simple_custom_annotation.configuration

import com.learn.dagger.simple_custom_annotation.CustomAnnotationActivity
import dagger.Component

@Component(modules = [CustomAnnotationModule::class])
interface CustomAnnotationComponent {
    fun inject(activity : CustomAnnotationActivity)
}