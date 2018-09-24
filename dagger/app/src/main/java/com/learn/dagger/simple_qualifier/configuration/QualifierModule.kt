package com.learn.dagger.simple_qualifier.configuration

import com.learn.dagger.simple_qualifier.domain.Communication
import com.learn.dagger.simple_qualifier.domain.HttpCommunication
import com.learn.dagger.simple_qualifier.domain.TcpCommunication
import dagger.Module
import dagger.Provides
import javax.inject.Named

const val HTTP = "http"
const val TCP = "TCP"
@Module
class QualifierModule {
    @Provides
    @Named(HTTP)
    fun getHttpCommunication() : Communication {
        return HttpCommunication()
    }

    @Provides
    @Named(TCP)
    fun getTcpCommunication() : Communication {
        return TcpCommunication()
    }
}