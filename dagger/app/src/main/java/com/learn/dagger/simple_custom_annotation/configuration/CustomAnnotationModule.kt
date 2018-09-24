package com.learn.dagger.simple_custom_annotation.configuration

import com.learn.dagger.simple_qualifier.configuration.HTTP
import com.learn.dagger.simple_qualifier.configuration.TCP
import com.learn.dagger.simple_qualifier.domain.Communication
import com.learn.dagger.simple_qualifier.domain.HttpCommunication
import com.learn.dagger.simple_qualifier.domain.TcpCommunication
import dagger.Module
import dagger.Provides

@Module
class CustomAnnotationModule {
    @Provides
    @Choose(HTTP)
    fun getHttpCommunication() : Communication {
        return HttpCommunication()
    }

    @Provides
    @Choose(TCP)
    fun getTcpCommunication() : Communication {
        return TcpCommunication()
    }
}