package com.rguzmanc.patagonian.domain.usecase

import com.rguzmanc.patagonian.domain.di.DomainCoroutineDispatchers
import com.rguzmanc.patagonian.domain.repository.PatagonianRepository
import com.rguzmanc.patagonian.domain.utils.TimeUtils
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import java.util.logging.Logger

class SyncLastSessionTimeUseCase(
    private val repository: PatagonianRepository,
    private val domainCoroutineDispatchers: DomainCoroutineDispatchers
) {
    companion object{
        private const val BACKGROUND_SESSION_TIME = 10 // in minutes
    }

    suspend operator fun invoke() = withContext(domainCoroutineDispatchers.io){
        val lastSessionTime = repository.getLastSessionTime().first()
        val now = System.currentTimeMillis()
        val minutesDiff = TimeUtils.getMinutesBetweenTimes(
            startDate = lastSessionTime,
            endDate = now
        )
        if(minutesDiff > BACKGROUND_SESSION_TIME) {
            repository.increment()
        }
    }
}