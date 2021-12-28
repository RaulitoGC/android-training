package com.rguzmanc.patagonian

import android.app.Application
import timber.log.Timber.DebugTree

import timber.log.Timber

open class PatagonianApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(DebugTree())
    }
}