package com.rguzmanc.friends.di

import com.rguzmanc.friends.core.ApplicationScope
import com.rguzmanc.friends.login.presentation.di.LoginComponent
import com.rguzmanc.friends.login.presentation.di.LoginModule
import com.rguzmanc.friends.persistence.di.DefaultPersistenceModule
import com.rguzmanc.friends.service.di.DefaultServiceModule
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        ApplicationModule::class,
        DefaultPersistenceModule::class, DefaultServiceModule::class,
        ViewModelModule::class,
        LoginModule::class
    ]
)
interface ApplicationComponent {
    fun loginComponent() : LoginComponent.Factory
}