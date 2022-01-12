package com.rguzmanc.friends.login.presentation.di

import androidx.lifecycle.ViewModel
import com.rguzmanc.friends.common.presentation.ViewModelKey
import com.rguzmanc.friends.login.presentation.email.EmailViewModel
import com.rguzmanc.friends.login.presentation.password.PasswordViewModel
import com.rguzmanc.friends.login.presentation.username.UsernameViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class LoginViewModelModule {

    @Provides
    @IntoMap
    @ViewModelKey(UsernameViewModel::class)
    fun provideUserNameViewModel(): ViewModel = UsernameViewModel()

    @Provides
    @IntoMap
    @ViewModelKey(EmailViewModel::class)
    fun provideEmailViewModel(): ViewModel = EmailViewModel()

    @Provides
    @IntoMap
    @ViewModelKey(PasswordViewModel::class)
    fun providePasswordViewModel(): ViewModel = PasswordViewModel()
}