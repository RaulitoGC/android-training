package com.rguzmanc.friends.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rguzmanc.friends.core.ApplicationScope
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.Multibinds
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Provider

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindsViewModelFactory(ViewModelFactory: ViewModelFactory): ViewModelProvider.Factory

}

class ViewModelFactory @Inject constructor(private val providers: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>) :
    ViewModelProvider.Factory {
    override fun <VM : ViewModel> create(modelClass: Class<VM>): VM {
        @Suppress("UNCHECKED_CAST")
        return providers[modelClass]?.let { viewModelProvider ->
            viewModelProvider.get() as VM
        } ?: run {
            throw IllegalArgumentException("$modelClass is not supported")
        }
    }
}