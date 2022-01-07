package com.rguzmanc.android_lifecycle.system_restoration

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.rguzmanc.android_lifecycle.R
import com.rguzmanc.android_lifecycle.sinngle_event.SingleEventActivity
import com.rguzmanc.android_lifecycle.sinngle_event.SingleEventModelFactory
import com.rguzmanc.android_lifecycle.sinngle_event.SingleEventViewModel

class RestorationActivity: AppCompatActivity() {

    companion object{
        val TAG = RestorationActivity::class.java.name
    }

    private val viewModel: RestorationViewModel by viewModels()

    lateinit var textView: TextView
    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restorationn)
        Log.d(TAG, "onCreate()")
        initUi()
        initViewModel()
    }

    private fun initUi() {
        textView = findViewById(R.id.tvContent)
        button = findViewById(R.id.btn_generate)
        button.setOnClickListener {
            viewModel.updateRandomString()
        }
    }

    private fun initViewModel() {
        viewModel.textViewContent.observe(this, {
            Log.d(TAG, "updating textview")
            textView.text = it
        })
    }

}