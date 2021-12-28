package com.rguzmanc.customview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * If you call [finish], then in the custom view
         * the method [CustomLayout.onAttachedToWindow] is not called
         */
        //finish()
    }
}