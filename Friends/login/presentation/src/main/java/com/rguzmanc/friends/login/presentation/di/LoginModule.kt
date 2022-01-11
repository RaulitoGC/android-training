package com.rguzmanc.friends.login.presentation.di

import com.rguzmanc.friends.core.ActivityScope
import com.rguzmanc.friends.login.data.LoginDataManager
import com.rguzmanc.friends.login.domain.LoginUsernameUseCase
import com.rguzmanc.friends.login.domain.system.LoginSystem
import dagger.Module
import dagger.Provides

@Module(subcomponents = [LoginComponent::class])
class LoginModule {

    @Provides
    @ActivityScope
    fun provideLoginUsernameUseCase(): LoginUsernameUseCase {
        return LoginUsernameUseCase()
    }


}