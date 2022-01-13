package com.rguzmanc.friends.login.presentation.username

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rguzmanc.friends.core.ActivityScope
import com.rguzmanc.friends.login.domain.LoginUsernameUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@ActivityScope
class UsernameViewModel @Inject constructor(private val loginUsernameUseCase: LoginUsernameUseCase) : ViewModel() {

    private val _login = MutableLiveData<Any>()
    val login: LiveData<Any> = _login

    private val _error = MutableLiveData<Any>()
    val error: LiveData<Any> = _error

    companion object{
        const val TAG = "TESTUsernameViewModel"
    }

    init {
        Log.d(TAG, "init()")
    }

    fun login(username: String) = viewModelScope.launch {
        val result = loginUsernameUseCase(username)
        if (result.isSuccess) {
            _login.value = result
        } else {
            _error.value = Any()
        }
    }
}