package com.learn.dagger.simple_static.domain

import javax.inject.Inject
import javax.inject.Singleton


class Person  {
    private var body : Body

    @Inject
    constructor(body : Body) {
        this.body = body
    }

    fun getBody() : Body {
        return body
    }
}