package com.rguzmanc.friends.di

import com.rguzmanc.friends.core.ApplicationScope
import com.rguzmanc.friends.login.presentation.di.LoginComponent
import com.rguzmanc.friends.persistence.di.DefaultPersistenceModule
import dagger.Component

@ApplicationScope
@Component(
    modules = [(ApplicationModule::class), (DefaultPersistenceModule::class)],

)
interface ApplicationComponent {
    fun loginComponent() : LoginComponent.Factory
}