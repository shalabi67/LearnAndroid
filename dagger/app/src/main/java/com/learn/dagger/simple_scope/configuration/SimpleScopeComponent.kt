package com.learn.dagger.simple_scope.configuration

import com.learn.dagger.simple_scope.SimpleScopeFactory
import dagger.Component

@SimpleScope
@Component
interface SimpleScopeComponent {
    fun inject(factory : SimpleScopeFactory)
}