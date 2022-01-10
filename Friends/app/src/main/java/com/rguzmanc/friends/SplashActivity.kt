package com.rguzmanc.friends

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rguzmanc.friends.di.ApplicationComponent
import com.rguzmanc.friends.di.ApplicationModule
import com.rguzmanc.friends.di.DaggerApplicationComponent
import dagger.Component

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var appComponent: ApplicationComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }
}