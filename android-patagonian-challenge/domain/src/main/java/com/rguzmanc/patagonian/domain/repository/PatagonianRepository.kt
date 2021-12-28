package com.rguzmanc.patagonian.domain.repository

import kotlinx.coroutines.flow.Flow

interface PatagonianRepository{

    fun getDeviceRotation()
    suspend fun getSessionCount() : Flow<Int>

    suspend fun increment()
}
