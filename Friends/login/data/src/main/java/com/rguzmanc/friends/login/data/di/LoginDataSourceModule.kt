package com.rguzmanc.friends.login.data.di

import com.rguzmanc.friends.core.ActivityScope
import com.rguzmanc.friends.login.data.LoginDataManager
import com.rguzmanc.friends.login.data.LoginDataSource
import com.rguzmanc.friends.login.data.local.LoginLocalDataSource
import com.rguzmanc.friends.login.data.remote.LoginRemoteDataSource
import com.rguzmanc.friends.login.domain.system.LoginSystem
import com.rguzmanc.friends.persistence.UserPreferences
import dagger.Module
import dagger.Provides

@Module
class LoginDataSourceModule {

    @Provides
    @ActivityScope
    fun provideLoginRemoteDataSource(userPreferences: UserPreferences): LoginDataSource.Local {
        return LoginLocalDataSource(userPreferences)
    }

    @Provides
    @ActivityScope
    fun provideLoginApi()

    @Provides
    @ActivityScope
    fun provideLoginLocalDataSource(): LoginDataSource.Remote {
        return LoginRemoteDataSource()
    }

    @Provides
    @ActivityScope
    fun provideLoginSystem(): LoginSystem {
        return LoginDataManager()
    }
}