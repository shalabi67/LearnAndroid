package com.learn.dagger.simple_custom_annotation.domain

class TcpCommunication : Communication {
    override fun getProtocol(): String {
        return "tcp"
    }
}