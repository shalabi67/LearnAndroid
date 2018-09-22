package com.learn.dagger.configuration

import com.learn.dagger.simple_example.SimpleExampleActivity
import com.learn.dagger.simple_module.configuration.PersonModule
import com.learn.dagger.simple_module.domain.Body
import com.learn.dagger.simple_module.domain.Person
import dagger.Component

@Component(modules = [PersonModule::class])
interface PersonComponent {
    fun getPerson() : Person
}