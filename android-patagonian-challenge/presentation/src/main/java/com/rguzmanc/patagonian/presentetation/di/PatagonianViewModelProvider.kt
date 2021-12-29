package com.rguzmanc.patagonian.presentetation.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rguzmanc.patagonian.presentetation.PatagonianViewModel

class PatagonianViewModelProvider(private val compositionRoot: CompositionRoot) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return PatagonianViewModel(
            getSessionCountUseCase = compositionRoot.getSessionCountUseCase,
            getLastSessionTimeUseCase = compositionRoot.getLastSessionTimeUseCase
        ) as T
    }
}