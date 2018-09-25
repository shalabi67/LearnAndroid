package com.learn.dagger.simple_subcomponent.configuration

import com.learn.dagger.simple_scope.domain.Person
import com.learn.dagger.simple_scope.domain.ScopedPerson
import dagger.Module
import dagger.Provides

@Module
class SimpleScopeModule {
    @Provides
    fun getScopedPerson() : ScopedPerson {
        return ScopedPerson()
    }

    @Provides
    fun getPerson() : Person {
        return Person()
    }
}