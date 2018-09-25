package com.learn.dagger.simple_custom_annotation.configuration

import javax.inject.Qualifier

@Qualifier
@MustBeDocumented
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class Choose(val value: String = "")