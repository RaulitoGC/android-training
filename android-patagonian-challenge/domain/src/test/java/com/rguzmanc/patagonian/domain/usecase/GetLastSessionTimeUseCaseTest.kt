package com.rguzmanc.patagonian.domain.usecase

import com.rguzmanc.patagonian.domain.di.DomainServiceLocator
import com.rguzmanc.patagonian.domain.repository.FakeRepository
import com.rguzmanc.patagonian.domain.repository.PatagonianRepository
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetLastSessionTimeUseCaseTest{
    private lateinit var getLastSessionTimeUseCase: GetLastSessionTimeUseCase
    private lateinit var repository: PatagonianRepository

    @Before
    fun setUp() {
        repository = FakeRepository()
        getLastSessionTimeUseCase = DomainServiceLocator.createGetLastSessionTimeUseCase(repository)
    }

    @Test
    fun `execute get last session time use case`() = runBlocking {
        val fakeSessionTime = 400L
        repository.setLastSessionTime(fakeSessionTime)

        val sessionTime = getLastSessionTimeUseCase().single()

        assert(fakeSessionTime == sessionTime)
    }
}