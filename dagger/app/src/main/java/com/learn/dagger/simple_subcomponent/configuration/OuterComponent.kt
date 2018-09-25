package com.learn.dagger.simple_subcomponent.configuration

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component
interface OuterComponent {
    fun getInnerComponent() : InnerScopeComponent
}