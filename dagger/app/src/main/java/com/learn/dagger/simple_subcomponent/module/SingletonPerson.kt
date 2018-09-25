package com.learn.dagger.simple_subcomponent.domain

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SingletonPerson @Inject constructor() {
    val count : Int = staticCounter++

}