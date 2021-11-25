package com.rguzmanc.android_lifecycle

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val periodTextData = MutableLiveData<OneTimeEvent<String>>()
    val dataWithInit = MutableLiveData("hello")
    val dataWithNoVal = MutableLiveData<String>()

    fun count() {
        viewModelScope.launch {
            repeat(5) {
                delay(5000)
                Log.e("MainViewModel", "value to fire: $it")
                periodTextData.value = "count: $it".toOneTimeEvent()
            }
        }
    }
}

class MainViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel() as T
    }
}
