package com.learn.dagger.simple_scope.domain

import javax.inject.Inject

var staticCounter = 0
class Person @Inject constructor() {
    val count : Int = staticCounter++

}