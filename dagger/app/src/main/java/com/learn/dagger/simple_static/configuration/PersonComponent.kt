package com.learn.dagger.simple_static.configuration


import com.learn.dagger.simple_static.domain.Person
import dagger.Component
import javax.inject.Singleton

@Component(modules = [PersonModule::class])
@Singleton
interface PersonComponent {
    fun getPerson() : Person
}