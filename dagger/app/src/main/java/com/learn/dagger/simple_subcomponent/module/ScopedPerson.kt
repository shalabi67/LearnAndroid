package com.learn.dagger.simple_subcomponent.domain


import com.learn.dagger.simple_subcomponent.configuration.InnerScope
import javax.inject.Inject

@InnerScope
class ScopedPerson @Inject constructor() {
    val count : Int = staticCounter++

}