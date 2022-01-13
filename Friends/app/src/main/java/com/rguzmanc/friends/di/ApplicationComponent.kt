package com.rguzmanc.friends.di

import com.rguzmanc.friends.core.ApplicationScope
import com.rguzmanc.friends.login.presentation.di.LoginComponent
import com.rguzmanc.friends.persistence.di.DefaultPersistenceModule
import com.rguzmanc.friends.service.di.DefaultServiceModule
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        ApplicationModule::class,
        DefaultServiceModule::class, DefaultPersistenceModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {
    fun loginComponentBuilder() : LoginComponent.Builder
}