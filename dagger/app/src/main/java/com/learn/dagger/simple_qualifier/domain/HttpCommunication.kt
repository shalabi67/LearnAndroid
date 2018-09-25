package com.learn.dagger.simple_qualifier.domain

class HttpCommunication : Communication {
    override fun getProtocol(): String {
        return "http"
    }
}