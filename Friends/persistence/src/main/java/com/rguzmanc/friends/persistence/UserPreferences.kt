package com.rguzmanc.friends.persistence

interface UserPreferences{
    fun getUsername(): String
    fun setUsername(username: String)

    fun isLoggedIn(): Boolean
    fun setIsLoggedIn(loggedIn: Boolean)

    fun clean()
}