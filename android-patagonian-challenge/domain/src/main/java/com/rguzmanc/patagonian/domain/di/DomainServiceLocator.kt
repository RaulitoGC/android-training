package com.rguzmanc.patagonian.domain.di

import com.rguzmanc.patagonian.domain.repository.PatagonianRepository
import com.rguzmanc.patagonian.domain.usecase.GetDeviceRotationUseCase
import com.rguzmanc.patagonian.domain.usecase.GetSessionCountUseCase

object DomainServiceLocator {

    fun createGetDeviceRotationUseCase(repository: PatagonianRepository) = GetDeviceRotationUseCase(
        repository = repository
    )

    fun createGetSessionCountUseCase(repository: PatagonianRepository) = GetSessionCountUseCase(
        repository = repository
    )
}