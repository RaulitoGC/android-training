package com.rguzmanc.friends.login.data.remote

import com.rguzmanc.friends.login.data.LoginDataSource

class LoginRemoteDataSource(private val loginApi: LoginApi): LoginDataSource.Remote {
    override fun login(username: String, email: String, password: String): Result<Any> {
        return Result.success(Any())
    }
}
