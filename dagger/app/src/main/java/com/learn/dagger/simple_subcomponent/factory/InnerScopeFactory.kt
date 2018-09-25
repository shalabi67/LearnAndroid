package com.learn.dagger.simple_subcomponent.factory

import com.learn.dagger.simple_subcomponent.domain.Person
import com.learn.dagger.simple_subcomponent.domain.ScopedPerson
import com.learn.dagger.simple_subcomponent.domain.SingletonPerson
import javax.inject.Inject

class InnerScopeFactory {
    @Inject
    lateinit var personOuter : SingletonPerson

    @Inject
    lateinit var personInner : ScopedPerson

    @Inject
    lateinit var person : Person
}
