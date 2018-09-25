package com.learn.dagger.simple_subcomponent.configuration

import javax.inject.Scope

@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class InnerScope {

}