package com.rguzmanc.patagonian.domain.usecase

import com.rguzmanc.patagonian.domain.di.DomainCoroutineDispatchers
import com.rguzmanc.patagonian.domain.repository.PatagonianRepository
import kotlinx.coroutines.flow.flowOn

class GetSessionCountUseCase(
    private val repository: PatagonianRepository,
    private val domainCoroutineDispatchers: DomainCoroutineDispatchers
) {

    suspend operator fun invoke() = repository.getSessionCount().flowOn(domainCoroutineDispatchers.io)
}