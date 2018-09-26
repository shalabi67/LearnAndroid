package com.learn.daggeradvanced.application.configuration

import com.learn.daggeradvanced.application.scopes.ActivityScope
import dagger.Subcomponent

@ActivityScope
@Subcomponent
interface ActivityComponent {
}