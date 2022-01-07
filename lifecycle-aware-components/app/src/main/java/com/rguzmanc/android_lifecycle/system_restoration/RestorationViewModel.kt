package com.rguzmanc.android_lifecycle.system_restoration

import android.util.Log
import androidx.lifecycle.*
import java.util.*

class RestorationViewModel(private val state: SavedStateHandle) : ViewModel() {

    private val _textViewContent = MutableLiveData<String>()
    val textViewContent: LiveData<String> = _textViewContent

    init {
        Log.d(RestorationActivity.TAG, "RestorationViewModel init")
        _textViewContent.value = "Init value"
    }

    fun updateRandomString() {
        _textViewContent.value = UUID.randomUUID().toString()
    }
}
