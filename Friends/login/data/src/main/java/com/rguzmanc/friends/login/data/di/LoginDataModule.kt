package com.rguzmanc.friends.login.data.di

import com.rguzmanc.friends.core.ActivityScope
import com.rguzmanc.friends.login.data.LoginDataManager
import com.rguzmanc.friends.login.data.LoginDataSource
import com.rguzmanc.friends.login.data.local.LoginLocalDataSource
import com.rguzmanc.friends.login.data.remote.LoginApi
import com.rguzmanc.friends.login.data.remote.LoginRemoteDataSource
import com.rguzmanc.friends.login.domain.system.LoginSystem
import com.rguzmanc.friends.persistence.UserPreferences
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class LoginDataModule {

    @Provides
    @ActivityScope
    fun provideLoginRemoteDataSource(userPreferences: UserPreferences): LoginDataSource.Local {
        return LoginLocalDataSource(userPreferences)
    }

    @Provides
    @ActivityScope
    fun provideLoginApi(retrofit: Retrofit) : LoginApi = retrofit.create(LoginApi::class.java)

    @Provides
    @ActivityScope
    fun provideLoginLocalDataSource(loginApi: LoginApi): LoginDataSource.Remote {
        return LoginRemoteDataSource(loginApi)
    }

    @Provides
    @ActivityScope
    fun provideLoginSystem(
        loginLocalDataSource: LoginDataSource.Local,
        loginRemoteDataSource: LoginDataSource.Remote
    ): LoginSystem {
        return LoginDataManager(loginRemoteDataSource, loginLocalDataSource)
    }
}