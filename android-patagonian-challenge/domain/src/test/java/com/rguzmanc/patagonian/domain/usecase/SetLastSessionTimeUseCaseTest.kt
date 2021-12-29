package com.rguzmanc.patagonian.domain.usecase

import com.rguzmanc.patagonian.domain.di.DomainServiceLocator
import com.rguzmanc.patagonian.domain.repository.FakeRepository
import com.rguzmanc.patagonian.domain.repository.PatagonianRepository
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class SetLastSessionTimeUseCaseTest{
    private lateinit var setLastSessionTimeUseCase: SetLastSessionTimeUseCase
    private lateinit var repository: PatagonianRepository

    @Before
    fun setUp() {
        repository = FakeRepository()
        setLastSessionTimeUseCase = DomainServiceLocator.createSetLastSessionTimeUseCase(repository)
    }

    @Test
    fun `execute get last session time use case`() = runBlocking {
        val fakeSessionTime = 400L

        setLastSessionTimeUseCase(fakeSessionTime)

        val sessionTime = repository.getLastSessionTime().single()
        assert(fakeSessionTime == sessionTime)
    }
}