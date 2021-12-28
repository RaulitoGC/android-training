package com.rguzmanc.patagonian.presentetation.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rguzmanc.patagonian.presentetation.PatagonianViewModel

class PatagonianViewModelProvider(private val compositionRoot: CompositionRoot) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return PatagonianViewModel(
            getDeviceRotationUseCase = compositionRoot.getDeviceRotationUseCase,
            getSessionCountUseCase = compositionRoot.getSessionCountUseCase
        ) as T
    }
}