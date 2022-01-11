package com.rguzmanc.friends.login.domain.error

sealed class LoginError{
    object Username: LoginError()
    object Password: LoginError()
    object Email: LoginError()
}
