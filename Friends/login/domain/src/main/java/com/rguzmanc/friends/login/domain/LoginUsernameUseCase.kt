package com.rguzmanc.friends.login.domain

import com.rguzmanc.friends.common.domain.AppCoroutineDispatchers
import com.rguzmanc.friends.login.domain.system.LoginSystem
import kotlinx.coroutines.withContext

class LoginUsernameUseCase(
    private val dispatcher: AppCoroutineDispatchers,
    private val loginSystem: LoginSystem
) {
    suspend operator fun invoke(username: String) : Result<Any> = withContext(dispatcher.computation) {
        return@withContext loginSystem.loginUsername(username)
    }
}