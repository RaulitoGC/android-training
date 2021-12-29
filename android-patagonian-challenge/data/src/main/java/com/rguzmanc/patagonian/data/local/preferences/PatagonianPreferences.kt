package com.rguzmanc.patagonian.data.local.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import com.rguzmanc.patagonian.domain.error.SessionError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import timber.log.Timber
import java.io.IOException
import java.time.LocalTime

interface PatagonianPreferences {

    suspend fun incrementSessionCount()
    suspend fun getSessionCount(): Flow<Int>

    suspend fun getLastSessionTime(): Flow<Long>
    suspend fun setLastSessionTime(sessionTime: Long)
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
        val LAST_SESSION_TIME = longPreferencesKey("prefs_patagonian_last_session_time")
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
                throw SessionError.SessionCountException()
            }
        }.map { preferences ->
            return@map preferences[PreferencesKeys.PATAGONIAN_COUNT] ?: DEFAULT_SESSION_COUNT
        }

    override suspend fun getLastSessionTime(): Flow<Long> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Timber.e(exception, "$TAG : Error getting last session Time.")
                emit(emptyPreferences())
            } else {
                throw SessionError.LastSessionTimeException()
            }
        }.map { preferences ->
            return@map preferences[PreferencesKeys.LAST_SESSION_TIME] ?: LocalTime.now().toNanoOfDay()
        }

    override suspend fun setLastSessionTime(sessionTime: Long) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.LAST_SESSION_TIME] = sessionTime
        }
    }
}