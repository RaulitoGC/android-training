package com.rguzmanc.patagonian.presentetation.di

import androidx.appcompat.app.AppCompatActivity
import com.rguzmanc.patagonian.data.DefaultPatagonianRepository
import com.rguzmanc.patagonian.data.di.DataServiceLocator
import com.rguzmanc.patagonian.domain.di.DomainServiceLocator

class CompositionRoot(appCompatActivity: AppCompatActivity) {

    val getLastSessionTimeUseCase = DomainServiceLocator.createGetLastSessionTimeUseCase(
        repository = DefaultPatagonianRepository(
            localDataSource = DataServiceLocator.getLocalDataSource(appCompatActivity)
        )
    )

    val getSessionCountUseCase = DomainServiceLocator.createGetSessionCountUseCase(
        repository = DefaultPatagonianRepository(
            localDataSource = DataServiceLocator.getLocalDataSource(appCompatActivity)
        )
    )

    val setLastTimeSessionUseCase = DomainServiceLocator.createSetLastSessionTimeUseCase(
        repository = DefaultPatagonianRepository(
            localDataSource = DataServiceLocator.getLocalDataSource(appCompatActivity)
        )
    )

    val syncSessionTimeUseCase = DomainServiceLocator.createSyncLastSessionTimeUseCase(
        repository = DefaultPatagonianRepository(
            localDataSource = DataServiceLocator.getLocalDataSource(appCompatActivity)
        )
    )
}