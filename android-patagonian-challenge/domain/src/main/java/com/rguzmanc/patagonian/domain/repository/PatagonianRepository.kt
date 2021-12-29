package com.rguzmanc.patagonian.domain.repository

import kotlinx.coroutines.flow.Flow

interface PatagonianRepository{

    suspend fun getSessionCount() : Flow<Int>

    suspend fun increment()

    suspend fun setLastSessionTime(sessionTime: Long)

    suspend fun getLastSessionTime(): Flow<Long>
}
