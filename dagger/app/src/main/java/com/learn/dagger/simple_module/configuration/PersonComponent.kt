package com.learn.dagger.simple_module.configuration

import com.learn.dagger.simple_module.domain.Person
import dagger.Component

@Component(modules = [PersonModule::class])
interface PersonComponent {
    fun getPerson() : Person
}