package com.rguzmanc.friends.login.data

import com.rguzmanc.friends.login.domain.system.LoginSystem
import kotlinx.coroutines.delay

class LoginDataManager(
    private val loginRemoteDataSource: LoginDataSource.Remote,
    private val loginLocalDataSource: LoginDataSource.Local
): LoginSystem {

    override suspend fun loginUsername(username: String) : Result<Any>{
        delay(2500L) // simulating task
        return Result.success(Any())
    }

    override suspend fun loginPassword(username: String, email: String, password: String): Result<Any> {
        val remote = loginRemoteDataSource.login(
            username = username,
            email = email,
            password = password
        )

        return if(remote.isSuccess){
            loginLocalDataSource.saveUsername(username)
            Result.success(Any())
        } else{
            Result.success(Exception())
        }
    }

    override suspend fun loginEmail(username: String, email: String) : Result<Any>{
        delay(2500L) // simulating task
        return Result.success(Any())
    }
}
