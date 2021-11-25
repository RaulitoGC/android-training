package com.rguzmanc.android_lifecycle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.distinctUntilChanged

class MainActivity : AppCompatActivity() {

    private val mViewModel: MainViewModel by lazy {
        ViewModelProvider(this, MainViewModelFactory())[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_second).setOnClickListener { Intent(this, SecondActivity::class.java).run { startActivity(this) } }

        mViewModel.periodTextData.distinctUntilChanged().observe(this, Observer {
            Log.e(TAG, "updated: $it")
            it.consumeOnce { message ->
                findViewById<TextView>(R.id.tvContent).text = message
            }
        })

        mViewModel.dataWithInit.observe(this, Observer {
            Log.e(TAG, "init received $it")
        })

        mViewModel.dataWithNoVal.observe(this, Observer {
            Log.e(TAG, "data with no val : $it")
        })

        mViewModel.count()
    }

    companion object {
        private val TAG = MainActivity::class.java.name
    }
}