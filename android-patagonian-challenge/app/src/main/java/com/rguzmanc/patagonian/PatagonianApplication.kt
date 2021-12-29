package com.rguzmanc.patagonian

import android.app.Application
import com.rguzmanc.patagonian.di.AppCoroutineScope
import timber.log.Timber
import timber.log.Timber.DebugTree

class PatagonianApplication : Application() {

    private lateinit var activityLifeCycle: PatagonianActivityLifeCycle

    override fun onCreate() {
        super.onCreate()
        Timber.plant(DebugTree())
        activityLifeCycle =
            PatagonianActivityLifeCycle(AppCoroutineScope.providesAppCoroutineScope())
        registerActivityLifecycleCallbacks(activityLifeCycle)
    }
}