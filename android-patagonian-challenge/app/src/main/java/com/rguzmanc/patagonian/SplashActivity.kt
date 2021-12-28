package com.rguzmanc.patagonian

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rguzmanc.patagonian.presentetation.PatagonianActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startActivity(PatagonianActivity.getIntent(this@SplashActivity))
        finish()
    }
}