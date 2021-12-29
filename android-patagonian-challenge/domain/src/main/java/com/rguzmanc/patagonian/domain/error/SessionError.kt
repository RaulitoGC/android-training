package com.rguzmanc.patagonian.domain.error

sealed class SessionError: Exception(){
    data class LastSessionTimeException(override val message: String? = null) : SessionError()
    data class SessionCountException(override val message: String? = null): SessionError()
}
