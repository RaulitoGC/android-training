package com.rguzmanc.patagonian.data

import com.rguzmanc.patagonian.data.local.LocalDataSource
import com.rguzmanc.patagonian.domain.repository.PatagonianRepository
import kotlinx.coroutines.flow.Flow

class DefaultPatagonianRepository(private val localDataSource: LocalDataSource): PatagonianRepository {

    override suspend fun getSessionCount() = localDataSource.getSessionCount()
    override suspend fun increment() {
        localDataSource.increment()
    }

    override suspend fun setLastSessionTime(sessionTime: Long) {
        localDataSource.setLastSessionTime(sessionTime)
    }

    override suspend fun getLastSessionTime(): Flow<Long> = localDataSource.getLastSessionTime()

}