package com.rguzmanc.patagonian.data.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.rguzmanc.patagonian.data.local.DefaultLocalDataSource
import com.rguzmanc.patagonian.data.local.LocalDataSource
import com.rguzmanc.patagonian.data.local.preferences.DefaultPatagonianPreferences

private const val USER_PREFERENCES_NAME = "patagonia_preferences"
private val Context.dataStore by preferencesDataStore(name = USER_PREFERENCES_NAME)

object DataServiceLocator {

    private val lock = Any()

    @Volatile
    private var localDataSource: LocalDataSource? = null

    fun getLocalDataSource(context: Context): LocalDataSource =
        localDataSource ?: synchronized(lock) {
            localDataSource ?: createLocalDataSource(context)
        }

    private fun createLocalDataSource(context: Context): LocalDataSource {
        return DefaultLocalDataSource(
            patagonianPreferences = DefaultPatagonianPreferences(dataStore = context.dataStore)
        ).also {
            localDataSource = it
        }
    }
}