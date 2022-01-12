package com.rguzmanc.friends.login.presentation

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.rguzmanc.friends.login.presentation.di.LoginComponent
import com.rguzmanc.friends.login.presentation.di.LoginComponentProvider
import com.rguzmanc.friends.login.presentation.username.UsernameFragment

class LoginActivity: AppCompatActivity() {

    lateinit var loginComponent: LoginComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginComponent = (application as LoginComponentProvider).provideLoginComponent()
        loginComponent.inject(this@LoginActivity)

        supportFragmentManager.commit {
            add(UsernameFragment(), UsernameFragment.TAG)
        }
    }
}