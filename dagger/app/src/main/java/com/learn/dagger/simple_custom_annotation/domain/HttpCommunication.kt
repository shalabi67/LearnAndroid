package com.learn.dagger.simple_custom_annotation.domain

class HttpCommunication : Communication {
    override fun getProtocol(): String {
        return "http"
    }
}