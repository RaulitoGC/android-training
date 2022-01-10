package com.rguzmanc.friends.di

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
@ApplicationScope
class ApplicationModule(private val applicationContext: Context) {

    @Provides
    @ApplicationScope
    fun provideApplicationContext() = applicationContext
}