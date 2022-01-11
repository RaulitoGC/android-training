package com.rguzmanc.friends.persistence.internal.preferences

import android.content.SharedPreferences
import com.rguzmanc.friends.persistence.UserPreferences

class DefaultUserPreferences(private val sharedPreferences: SharedPreferences) :
    UserPreferences {

    companion object{
        const val KEY_USERNAME = "SHARED_PREFERENCES_USERNAME_KEY"
    }

    override fun getUsername(): String {
        TODO("Not yet implemented")
    }

    override fun setUsername(username: String) {
        TODO("Not yet implemented")
    }

    override fun isLoggedIn(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setIsLoggedIn(loggedIn: Boolean) {
        TODO("Not yet implemented")
    }


    override fun clean() {
        TODO("Not yet implemented")
    }

}