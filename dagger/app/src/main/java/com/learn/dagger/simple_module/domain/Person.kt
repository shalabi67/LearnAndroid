package com.learn.dagger.simple_module.domain

import javax.inject.Inject

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