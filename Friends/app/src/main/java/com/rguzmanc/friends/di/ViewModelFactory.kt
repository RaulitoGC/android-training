package com.rguzmanc.friends.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import java.lang.IllegalArgumentException
import javax.inject.Inject
import javax.inject.Provider

@Module
abstract class ViewModelModule{

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}

class ViewModelFactory @Inject constructor(private val providers: Map<Class<out ViewModel>, Provider<ViewModel>>): ViewModelProvider.Factory {
    override fun <VM : ViewModel> create(modelClass: Class<VM>): VM {
        @Suppress("UNCHECKED_CAST")
        return providers[modelClass]?.let { viewModelProvider ->
            viewModelProvider.get() as VM
        } ?: run {
            throw IllegalArgumentException("$modelClass is not supported")
        }
    }
}