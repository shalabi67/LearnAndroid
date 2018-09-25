package com.learn.dagger.simple_static.configuration

import com.learn.dagger.simple_static.domain.Body
import com.learn.dagger.simple_static.domain.Person
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PersonModule {
    @Provides
    @Singleton
    fun getBody() : Body {
        Body.count++
        return Body()
    }

    @Provides
    @Singleton
    fun getPerson(body : Body) : Person {
        return Person(body)
    }

    companion object {
        val personComponent : PersonComponent = DaggerPersonComponent.create();
    }
}