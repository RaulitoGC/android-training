package com.rguzmanc.friends.login.domain.system

interface LoginSystem {

    suspend fun loginUsername(username: String): Result<Any>

    suspend fun loginPassword(username: String, email: String, password: String): Result<Any>

    suspend fun loginEmail(username: String, email: String) : Result<Any>
}