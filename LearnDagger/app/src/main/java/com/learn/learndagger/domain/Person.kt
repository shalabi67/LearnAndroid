package com.learn.learndagger.domain

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