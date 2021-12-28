package com.rguzmanc.patagonian.data.local

import com.rguzmanc.patagonian.data.local.preferences.PatagonianPreferences
import com.rguzmanc.patagonian.domain.repository.PatagonianRepository

interface LocalDataSource : PatagonianRepository

class DefaultLocalDataSource(private val patagonianPreferences: PatagonianPreferences) :
    LocalDataSource {
    override fun getDeviceRotation() {

    }

    override suspend fun getSessionCount() = patagonianPreferences.getSessionCount()
    override suspend fun increment() =
        patagonianPreferences.incrementSessionCount()

}