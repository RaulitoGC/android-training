package com.rguzmanc.patagonian.domain.usecase

import com.rguzmanc.patagonian.domain.di.DomainServiceLocator
import com.rguzmanc.patagonian.domain.repository.FakeRepository
import com.rguzmanc.patagonian.domain.repository.PatagonianRepository
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetSessionCountUseCaseTest{
    private lateinit var getSessionCountUseCase: GetSessionCountUseCase
    private lateinit var repository: PatagonianRepository

    @Before
    fun setUp() {
        repository = FakeRepository()
        getSessionCountUseCase = DomainServiceLocator.createGetSessionCountUseCase(repository)
    }

    @Test
    fun `execute get last session time use case`() = runBlocking {
        val fakeSessionCount = 4
        FakeRepository.setSessionCount(fakeSessionCount)

        val sessionCount = getSessionCountUseCase.invoke().single()

        assert(fakeSessionCount == sessionCount)
    }
}