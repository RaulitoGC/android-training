package com.rguzmanc.friends.login.presentation.di

import androidx.lifecycle.ViewModel
import com.rguzmanc.friends.common.presentation.ViewModelKey
import com.rguzmanc.friends.login.domain.LoginUsernameUseCase
import com.rguzmanc.friends.login.presentation.email.EmailViewModel
import com.rguzmanc.friends.login.presentation.username.UsernameViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Named

@Module
abstract class LoginPresentationModule {

    @Binds
    @IntoMap
    @ViewModelKey(UsernameViewModel::class)
    abstract fun provideUserNameViewModel(usernameViewModel: UsernameViewModel): ViewModel

//
//    @IntoMap
//    @Binds
//    @ViewModelKey(PasswordViewModel::class)
//    abstract fun providePasswordViewModel(viewModel: PasswordViewModel): ViewModel
}