package com.rguzmanc.patagonian.data.local

import com.rguzmanc.patagonian.data.local.preferences.PatagonianPreferences
import com.rguzmanc.patagonian.domain.repository.PatagonianRepository
import kotlinx.coroutines.flow.Flow

interface LocalDataSource : PatagonianRepository

class DefaultLocalDataSource(private val patagonianPreferences: PatagonianPreferences) :
    LocalDataSource {

    override suspend fun getSessionCount() = patagonianPreferences.getSessionCount()

    override suspend fun increment() = patagonianPreferences.incrementSessionCount()

    override suspend fun setLastSessionTime(sessionTime: Long) {
        patagonianPreferences.setLastSessionTime(sessionTime)
    }
    override suspend fun getLastSessionTime(): Flow<Long> = patagonianPreferences.getLastSessionTime()
}