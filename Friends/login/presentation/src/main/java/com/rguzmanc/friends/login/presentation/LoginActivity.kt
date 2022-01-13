package com.rguzmanc.friends.login.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.rguzmanc.friends.login.presentation.di.LoginComponent
import com.rguzmanc.friends.login.presentation.di.LoginComponentProvider
import com.rguzmanc.friends.login.presentation.username.UsernameFragment

class LoginActivity : AppCompatActivity() {

    companion object{
        const val TAG = "TESTLoginActivity"
    }

    val loginComponent: LoginComponent by lazy {
        (application as LoginComponentProvider).getLoginComponentBuilder().build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate()")
        loginComponent.inject(this@LoginActivity)

        if(savedInstanceState == null) {
            supportFragmentManager.commit {
                replace(android.R.id.content, UsernameFragment.newInstance(), UsernameFragment.TAG)
            }
        }

    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy()")
    }

}