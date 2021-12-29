package com.rguzmanc.patagonian.domain.usecase

import com.rguzmanc.patagonian.domain.di.DomainCoroutineDispatchers
import com.rguzmanc.patagonian.domain.repository.PatagonianRepository
import kotlinx.coroutines.flow.flowOn

class GetLastSessionTimeUseCase(
    private val repository: PatagonianRepository,
    private val domainCoroutineDispatchers: DomainCoroutineDispatchers
) {

    suspend operator fun invoke() =
        repository.getLastSessionTime().flowOn(domainCoroutineDispatchers.io)
}