package com.rguzmanc.patagonian

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rguzmanc.patagonian.presentetation.di.CompositionRoot
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class PatagonianActivityLifeCycle(
    private val coroutineScope: CoroutineScope
) : Application.ActivityLifecycleCallbacks {

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {

    }

    override fun onActivityStarted(activity: Activity) {

    }

    override fun onActivityResumed(activity: Activity) {
        if (activity is AppCompatActivity) {
            val compositionRoot = CompositionRoot(activity)
            coroutineScope.launch {
                compositionRoot.syncSessionTimeUseCase.invoke()
            }
        }
    }

    /**
     * Track when the app is going to background
     */
    override fun onActivityPaused(activity: Activity) {
        if (activity is AppCompatActivity) {
            val compositionRoot = CompositionRoot(activity)
            coroutineScope.launch {
                val time : Long = System.currentTimeMillis()
                compositionRoot.setLastTimeSessionUseCase(time)
            }
        }
    }

    override fun onActivityStopped(activity: Activity) {

    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

    }

    override fun onActivityDestroyed(activity: Activity) {

    }
}