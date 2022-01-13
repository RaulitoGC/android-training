package com.rguzmanc.friends.login.domain.di

import com.rguzmanc.friends.common.domain.AppCoroutineDispatchers
import com.rguzmanc.friends.core.ActivityScope
import com.rguzmanc.friends.login.domain.LoginEmailUseCase
import com.rguzmanc.friends.login.domain.LoginPasswordUseCase
import com.rguzmanc.friends.login.domain.LoginUsernameUseCase
import com.rguzmanc.friends.login.domain.system.LoginSystem
import dagger.Module
import dagger.Provides

@Module
class LoginDomainModule {

    @Provides
    @ActivityScope
    fun provideLoginUsernameUseCase(
        appCoroutineDispatcher: AppCoroutineDispatchers,
        loginSystem: LoginSystem
    ): LoginUsernameUseCase {
        return LoginUsernameUseCase(appCoroutineDispatcher, loginSystem)
    }

    @Provides
    @ActivityScope
    fun provideLoginEmailUseCase(
        appCoroutineDispatcher: AppCoroutineDispatchers,
        loginSystem: LoginSystem
    ): LoginEmailUseCase {
        return LoginEmailUseCase(appCoroutineDispatcher, loginSystem)
    }

    @Provides
    @ActivityScope
    fun provideLoginPasswordUseCase(
        appCoroutineDispatcher: AppCoroutineDispatchers,
        loginSystem: LoginSystem
    ): LoginPasswordUseCase {
        return LoginPasswordUseCase(appCoroutineDispatcher, loginSystem)
    }
}