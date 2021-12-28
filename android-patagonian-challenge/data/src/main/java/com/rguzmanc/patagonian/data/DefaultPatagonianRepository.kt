package com.rguzmanc.patagonian.data

import com.rguzmanc.patagonian.data.local.LocalDataSource
import com.rguzmanc.patagonian.domain.repository.PatagonianRepository

class DefaultPatagonianRepository(private val localDataSource: LocalDataSource): PatagonianRepository {
    override fun getDeviceRotation() {

    }

    override suspend fun getSessionCount() = localDataSource.getSessionCount()
    override suspend fun increment() {
        localDataSource.increment()
    }

}