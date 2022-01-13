package com.rguzmanc.friends

import android.app.Application
import com.rguzmanc.friends.di.ApplicationComponent
import com.rguzmanc.friends.di.ApplicationModule
import com.rguzmanc.friends.di.DaggerApplicationComponent
import com.rguzmanc.friends.login.presentation.di.LoginComponent
import com.rguzmanc.friends.login.presentation.di.LoginComponentProvider

class FriendsApplication: Application(), LoginComponentProvider {

    private lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    override fun getLoginComponentBuilder(): LoginComponent.Builder {
        return appComponent.loginComponentBuilder()
    }
}