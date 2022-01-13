package com.rguzmanc.friends.login.presentation.di

import androidx.appcompat.app.AppCompatActivity
import com.rguzmanc.friends.core.ActivityScope
import com.rguzmanc.friends.login.data.di.LoginDataModule
import com.rguzmanc.friends.login.domain.di.LoginDomainModule
import com.rguzmanc.friends.login.presentation.email.EmailFragment
import com.rguzmanc.friends.login.presentation.password.PasswordFragment
import com.rguzmanc.friends.login.presentation.username.UsernameFragment
import dagger.Subcomponent

@ActivityScope
@Subcomponent(
    modules = [
        LoginPresentationModule::class,
        LoginDomainModule::class,
        LoginDataModule::class
    ]
)
interface LoginComponent {

    @Subcomponent.Builder
    interface Builder{
        fun build(): LoginComponent
    }

    fun inject(loginActivity: AppCompatActivity)

    fun inject(usernameFragment: UsernameFragment)
    fun inject(emailFragment: EmailFragment)
    fun inject(passwordFragment: PasswordFragment)
}