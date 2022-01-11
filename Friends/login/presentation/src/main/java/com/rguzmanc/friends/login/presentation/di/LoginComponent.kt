package com.rguzmanc.friends.login.presentation.di

import androidx.appcompat.app.AppCompatActivity
import com.rguzmanc.friends.core.ActivityScope
import dagger.Component
import dagger.Subcomponent

@ActivityScope
@Subcomponent
interface LoginComponent {

    @Subcomponent.Factory
    interface Factory{
        fun create(): LoginComponent
    }

    fun inject(loginActivity: AppCompatActivity)
}