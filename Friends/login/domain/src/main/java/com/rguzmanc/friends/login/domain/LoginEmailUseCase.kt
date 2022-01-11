package com.rguzmanc.friends.login.domain

import com.rguzmanc.friends.common.domain.AppCoroutineDispatchers
import com.rguzmanc.friends.login.domain.system.LoginSystem
import kotlinx.coroutines.withContext

class LoginEmailUseCase(
    private val dispatcher: AppCoroutineDispatchers,
    private val loginSystem: LoginSystem
) {

    suspend operator fun invoke(username: String, email: String) = withContext(dispatcher.computation){
        return@withContext loginSystem.loginEmail(username = username, email = email)
    }
}