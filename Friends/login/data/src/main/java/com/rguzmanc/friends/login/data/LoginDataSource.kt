package com.rguzmanc.friends.login.data

sealed interface LoginDataSource{

    interface Local{
        fun saveUsername(username: String)
        fun cleanUsername()
    }

    interface Remote{
        fun login(username: String, email: String, password: String) : Result<Any>
    }
}