package com.rguzmanc.friends.login.domain

import com.rguzmanc.friends.common.domain.AppCoroutineDispatchers
import com.rguzmanc.friends.login.domain.system.LoginSystem
import kotlinx.coroutines.withContext

class LoginPasswordUseCase(
    private val dispatcher: AppCoroutineDispatchers,
    private val loginSystem: LoginSystem
) {

    suspend operator fun invoke(username: String, email: String, password: String) = withContext(dispatcher.computation){
        return@withContext loginSystem.loginPassword(
            username = username,
            email = email,
            password = password
        )
    }
}