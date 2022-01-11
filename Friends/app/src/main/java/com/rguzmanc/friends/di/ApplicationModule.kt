package com.rguzmanc.friends.di

import android.content.Context
import android.content.SharedPreferences
import com.rguzmanc.friends.common.domain.AppCoroutineDispatchers
import com.rguzmanc.friends.core.ApplicationScope
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers

@Module
class ApplicationModule(private val applicationContext: Context) {

    @Provides
    @ApplicationScope
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
}