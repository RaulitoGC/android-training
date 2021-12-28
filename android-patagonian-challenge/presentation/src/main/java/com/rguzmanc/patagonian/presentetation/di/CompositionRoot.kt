package com.rguzmanc.patagonian.presentetation.di

import androidx.appcompat.app.AppCompatActivity
import com.rguzmanc.patagonian.data.DefaultPatagonianRepository
import com.rguzmanc.patagonian.data.di.DataServiceLocator
import com.rguzmanc.patagonian.domain.di.DomainServiceLocator

class CompositionRoot(appCompatActivity: AppCompatActivity) {

    val getDeviceRotationUseCase = DomainServiceLocator.createGetDeviceRotationUseCase(
        repository = DefaultPatagonianRepository(
            localDataSource = DataServiceLocator.getLocalDataSource(appCompatActivity)
        )
    )

    val getSessionCountUseCase = DomainServiceLocator.createGetSessionCountUseCase(
        repository = DefaultPatagonianRepository(
            localDataSource = DataServiceLocator.getLocalDataSource(appCompatActivity)
        )
    )
}