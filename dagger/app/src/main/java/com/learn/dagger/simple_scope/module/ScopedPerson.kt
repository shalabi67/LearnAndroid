package com.learn.dagger.simple_scope.domain

import com.learn.dagger.simple_scope.configuration.SimpleScope
import javax.inject.Inject

@SimpleScope
class ScopedPerson @Inject constructor() {
    val count : Int = staticCounter++

}