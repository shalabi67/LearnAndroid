package com.learn.dagger.configuration

import com.learn.dagger.simple_example.SimpleExampleActivity
import dagger.Component

@Component
interface SimpleExampleComponent {
    fun inject(mainActivity : SimpleExampleActivity)
}