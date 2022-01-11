package com.rguzmanc.friends.persistence.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.rguzmanc.friends.core.ApplicationScope
import com.rguzmanc.friends.persistence.AppDataBase
import com.rguzmanc.friends.persistence.UserPreferences
import com.rguzmanc.friends.persistence.internal.database.FriendAppDataBase
import com.rguzmanc.friends.persistence.internal.preferences.DefaultUserPreferences
import dagger.Module
import dagger.Provides

@Module
class DefaultPersistenceModule {

    @Provides
    @ApplicationScope
    fun provideDataBase(context: Context): AppDataBase = Room.databaseBuilder(
        context,
        FriendAppDataBase::class.java, "database-name"
    ).build()

    @Provides
    @ApplicationScope
    fun provideUserPreferences(sharedPreferences: SharedPreferences): UserPreferences =
        DefaultUserPreferences(sharedPreferences)
}