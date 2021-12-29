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

    /**
     * When any activity is being resumed, the app is sync if the session counter needs to be incremented
     */
    override fun onActivityResumed(activity: Activity) {
        if (activity is AppCompatActivity) {
            val compositionRoot = CompositionRoot(activity)
            coroutineScope.launch {
                compositionRoot.syncSessionTimeUseCase.invoke()
            }
        }
    }

    /**
     * When the app is being paused, the app is saving this time in milliseconds.
     * This will allow sync in [onActivityResumed] if the counter needs to be incremented based on
     * the business rules.
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