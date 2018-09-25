package com.learn.dagger.simple_scope

import com.learn.dagger.simple_scope.domain.Person
import com.learn.dagger.simple_scope.domain.ScopedPerson
import javax.inject.Inject

class SimpleScopeFactory {
    @Inject
    lateinit var personStatic : ScopedPerson

    @Inject
    lateinit var person : Person
}