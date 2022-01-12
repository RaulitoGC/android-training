package com.rguzmanc.friends.di

import android.content.Context
import android.content.SharedPreferences
import com.rguzmanc.friends.BuildConfig
import com.rguzmanc.friends.common.domain.AppCoroutineDispatchers
import com.rguzmanc.friends.core.ApiEndPoint
import com.rguzmanc.friends.core.ApplicationContext
import com.rguzmanc.friends.core.ApplicationScope
import com.rguzmanc.friends.core.BuildConfigDebug
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers

@Module
class ApplicationModule(private val applicationContext: Context) {

    @Provides
    @ApplicationScope
    @ApplicationContext
    fun provideApplicationContext() = applicationContext

    @Provides
    @ApplicationScope
    fun provideAppCoroutineDispatcher() = AppCoroutineDispatchers(
        io = Dispatchers.IO,
        computation = Dispatchers.Default,
        main = Dispatchers.Main
    )

    @Provides
    @ApplicationScope
    fun provideSharedPreferences(context: Context): SharedPreferences =
        context.getSharedPreferences("friends-shared-preferences", Context.MODE_PRIVATE)

    @Provides
    @ApplicationScope
    @ApiEndPoint
    // TODO : Move this to gradle file
    fun provideApiEndpoint() : String = "https://private-908651-friends15.apiary-mock.com/"

    @Provides
    @ApplicationScope
    @BuildConfigDebug
    fun provideBuildConfigDebug(): Boolean = BuildConfig.DEBUG
}