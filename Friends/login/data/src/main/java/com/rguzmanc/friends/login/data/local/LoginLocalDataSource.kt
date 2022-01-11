package com.rguzmanc.friends.login.data.local

import com.rguzmanc.friends.login.data.LoginDataSource
import com.rguzmanc.friends.persistence.UserPreferences

class LoginLocalDataSource constructor(private val userPreferences: UserPreferences): LoginDataSource.Local {
    override fun saveUsername(username: String) {
        userPreferences.setUsername(username)
    }

    override fun cleanUsername() {
        userPreferences.clean()
    }
}