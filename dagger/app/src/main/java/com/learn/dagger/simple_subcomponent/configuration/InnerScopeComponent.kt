package com.learn.dagger.simple_subcomponent.configuration


import com.learn.dagger.simple_subcomponent.factory.InnerScopeFactory
import dagger.Subcomponent

@InnerScope
@Subcomponent
interface InnerScopeComponent {
    fun inject(factory : InnerScopeFactory)
}