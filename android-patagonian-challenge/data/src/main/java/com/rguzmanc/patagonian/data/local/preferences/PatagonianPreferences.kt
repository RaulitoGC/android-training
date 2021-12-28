package com.rguzmanc.patagonian.data.local.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import timber.log.Timber
import java.io.IOException

interface PatagonianPreferences {

    suspend fun incrementSessionCount()
    suspend fun getSessionCount(): Flow<Int>
}

class DefaultPatagonianPreferences(private val dataStore: DataStore<Preferences>) :
    PatagonianPreferences {

    companion object {
        private const val DEFAULT_SESSION_COUNT = 1
        private const val INCREMENT_SESSION_RANGE = 1
        private const val TAG = "DefaultPatagonianPreferences"
    }

    private object PreferencesKeys {
        val PATAGONIAN_COUNT = intPreferencesKey("prefs_patagonian_counter_key")
    }

    override suspend fun incrementSessionCount() {
        dataStore.edit { preferences ->
            val current = preferences[PreferencesKeys.PATAGONIAN_COUNT] ?: DEFAULT_SESSION_COUNT
            preferences[PreferencesKeys.PATAGONIAN_COUNT] = current.plus(INCREMENT_SESSION_RANGE)
        }
    }

    override suspend fun getSessionCount(): Flow<Int> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Timber.e(exception, "$TAG : Error reading preferences.")
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            preferences[PreferencesKeys.PATAGONIAN_COUNT] ?: DEFAULT_SESSION_COUNT
        }
}