package com.learn.learndagger.configuration

import com.learn.learndagger.MainActivity
import dagger.Component

@Component
interface MainComponent {
    fun inject(mainActivity : MainActivity)
}