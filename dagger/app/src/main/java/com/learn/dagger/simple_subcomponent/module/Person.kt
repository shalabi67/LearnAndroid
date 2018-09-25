package com.learn.dagger.simple_subcomponent.domain

import javax.inject.Inject

var staticCounter = 0
class Person @Inject constructor() {
    val count : Int = staticCounter++

}