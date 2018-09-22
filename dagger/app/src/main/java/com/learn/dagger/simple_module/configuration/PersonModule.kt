package com.learn.dagger.simple_module.configuration

import com.learn.dagger.simple_module.domain.Body
import com.learn.dagger.simple_module.domain.Person
import dagger.Module
import dagger.Provides

@Module
class PersonModule {
    @Provides
    fun getBody() : Body {
        return Body()
    }

    @Provides
    fun getPerson(body : Body) : Person {
        return Person(body)
    }

}