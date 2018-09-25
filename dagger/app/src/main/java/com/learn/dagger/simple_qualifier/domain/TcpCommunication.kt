package com.learn.dagger.simple_qualifier.domain

class TcpCommunication : Communication {
    override fun getProtocol(): String {
        return "tcp"
    }
}