package com.rguzmanc.friends.login.presentation.di

import androidx.appcompat.app.AppCompatActivity
import com.rguzmanc.friends.core.ActivityScope
import com.rguzmanc.friends.login.presentation.email.EmailFragment
import com.rguzmanc.friends.login.presentation.password.PasswordFragment
import com.rguzmanc.friends.login.presentation.username.UsernameFragment
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

    fun inject(usernameFragment: UsernameFragment)
    fun inject(emailFragment: EmailFragment)
    fun inject(passwordFragment: PasswordFragment)
}