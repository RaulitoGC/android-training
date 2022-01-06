package com.rguzmanc.android_lifecycle.sinngle_event

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rguzmanc.android_lifecycle.MainViewModel
import com.rguzmanc.android_lifecycle.OneTimeEvent
import com.rguzmanc.android_lifecycle.sinngle_event.SingleEventActivity.Companion.TAG

class SingleEventViewModel: ViewModel() {
    private val _showSnackBar = MutableLiveData<Boolean>()
    val showSnackBar : LiveData<Boolean> = _showSnackBar

    init {
        Log.d(TAG, "SingleEventViewModel init")
        _showSnackBar.value = true
    }

}

class SingleEventModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SingleEventViewModel() as T
    }
}