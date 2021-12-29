package com.rguzmanc.patagonian.domain.di

import com.rguzmanc.patagonian.domain.repository.PatagonianRepository
import com.rguzmanc.patagonian.domain.usecase.GetLastSessionTimeUseCase
import com.rguzmanc.patagonian.domain.usecase.GetSessionCountUseCase
import com.rguzmanc.patagonian.domain.usecase.SetLastSessionTimeUseCase
import com.rguzmanc.patagonian.domain.usecase.SyncLastSessionTimeUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

object DomainServiceLocator {

    private val dispatchers = DomainCoroutineDispatchers(
        io = Dispatchers.IO,
        computation = Dispatchers.Default,
        main = Dispatchers.Main
    )

    fun createGetSessionCountUseCase(repository: PatagonianRepository) = GetSessionCountUseCase(
        repository = repository,
        domainCoroutineDispatchers = dispatchers
    )

    fun createSetLastSessionTimeUseCase(repository: PatagonianRepository) = SetLastSessionTimeUseCase(
        repository = repository,
        domainCoroutineDispatchers = dispatchers
    )

    fun createGetLastSessionTimeUseCase(repository: PatagonianRepository) = GetLastSessionTimeUseCase(
        repository = repository,
        domainCoroutineDispatchers = dispatchers
    )

    fun createSyncLastSessionTimeUseCase(repository: PatagonianRepository) = SyncLastSessionTimeUseCase(
        repository = repository,
        domainCoroutineDispatchers = dispatchers
    )
}

data class DomainCoroutineDispatchers(
    val io: CoroutineDispatcher,
    val computation: CoroutineDispatcher,
    val main: CoroutineDispatcher
)