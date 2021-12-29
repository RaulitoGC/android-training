package com.rguzmanc.patagonian.domain.usecase

import com.rguzmanc.patagonian.domain.di.DomainServiceLocator
import com.rguzmanc.patagonian.domain.repository.FakeRepository
import com.rguzmanc.patagonian.domain.repository.PatagonianRepository
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class SyncLastSessionTimeUseCaseTest{
    private lateinit var syncLastSessionTimeUseCase: SyncLastSessionTimeUseCase
    private lateinit var repository: PatagonianRepository

    @Before
    fun setUp() {
        repository = FakeRepository()
        syncLastSessionTimeUseCase = DomainServiceLocator.createSyncLastSessionTimeUseCase(repository)
    }

    @Test
    fun `execute sync last session time use case with more than 10 minutes`() = runBlocking {
        val sessionCount = repository.getSessionCount().single()
        val twelveMinutes = 12.times(60 * 1000) // 12 minutes
        repository.setLastSessionTime(System.currentTimeMillis() - twelveMinutes)

        syncLastSessionTimeUseCase.invoke()
        val sessionCountAfterSync = repository.getSessionCount().single()

        assert(sessionCount + 1 == sessionCountAfterSync)
    }

    @Test
    fun `execute sync last session time use case with less than 10 minutes`() = runBlocking {
        val sessionCount = repository.getSessionCount().single()
        repository.setLastSessionTime(System.currentTimeMillis())

        syncLastSessionTimeUseCase.invoke()
        val sessionCountAfterSync = repository.getSessionCount().single()

        assert(sessionCount == sessionCountAfterSync)
    }
}