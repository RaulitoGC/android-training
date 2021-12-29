package com.rguzmanc.patagonian.presentetation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rguzmanc.patagonian.domain.usecase.GetLastSessionTimeUseCase
import com.rguzmanc.patagonian.domain.usecase.GetSessionCountUseCase
import com.rguzmanc.patagonian.presentetation.util.MathUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

class PatagonianViewModel(
    private val getSessionCountUseCase: GetSessionCountUseCase,
    private val getLastSessionTimeUseCase: GetLastSessionTimeUseCase
) : ViewModel() {

    companion object {
        const val DEFAULT_SESSION_COUNT = 1
    }

    private val _sessionCount = MutableStateFlow(DEFAULT_SESSION_COUNT)
    val sessionCount: StateFlow<Int> = _sessionCount

    private val _textSize = MutableStateFlow(R.dimen.default_text_size)
    val textSize: StateFlow<Int> = _textSize

    init {
        viewModelScope.launch {
            getSessionCountUseCase.invoke()
                .catch { throwable ->
                    Timber.e(throwable)
                }.collect { count ->
                    _sessionCount.value = count
                }
        }
    }

    fun getDimension(dz: Float) = viewModelScope.launch {
        val degrees = MathUtils.toDegrees(dz)

        val sizeId = when {
            degrees > 210.0 -> {
                R.dimen.small_text_size
            }
            degrees < 150.0 -> {
                R.dimen.big_text_size
            }
            else -> {
                R.dimen.default_text_size
            }
        }
        _textSize.value = sizeId
    }
}