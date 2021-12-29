package com.rguzmanc.patagonian.domain.usecase

import com.rguzmanc.patagonian.domain.di.DomainCoroutineDispatchers
import com.rguzmanc.patagonian.domain.repository.PatagonianRepository
import kotlinx.coroutines.withContext

class SetLastSessionTimeUseCase(
    private val repository: PatagonianRepository,
    private val domainCoroutineDispatchers: DomainCoroutineDispatchers
) {
    suspend operator fun invoke(sessionTime: Long) = withContext(domainCoroutineDispatchers.io) {
        repository.setLastSessionTime(sessionTime)
    }
}