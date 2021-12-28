package com.rguzmanc.patagonian.domain.usecase

import com.rguzmanc.patagonian.domain.repository.PatagonianRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn

class GetDeviceRotationUseCase(private val repository: PatagonianRepository) {

    suspend operator fun invoke() = repository.increment()
}