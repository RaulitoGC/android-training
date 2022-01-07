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
import com.rguzmanc.android_lifecycle.sinngle_event.SingleEventActivity
import com.rguzmanc.android_lifecycle.system_restoration.RestorationActivity

class MainActivity : AppCompatActivity() {

    private val mViewModel: MainViewModel by lazy {
        ViewModelProvider(this, MainViewModelFactory())[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.e(TAG, "onCreate()")

        findViewById<Button>(R.id.btn_second).setOnClickListener {
            Intent(this,RestorationActivity::class.java).run { startActivity(this)
            } }

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

    override fun onStart() {
        super.onStart()
        Log.e(TAG, "onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG, "onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.e(TAG, "onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.e(TAG, "onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy()")
    }

    companion object {
        private val TAG = MainActivity::class.java.name
    }
}