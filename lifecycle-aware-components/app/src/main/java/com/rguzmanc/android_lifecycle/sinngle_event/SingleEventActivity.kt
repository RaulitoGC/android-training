package com.rguzmanc.android_lifecycle.sinngle_event

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.rguzmanc.android_lifecycle.R

class SingleEventActivity : AppCompatActivity() {

    companion object{
        val TAG = SingleEventActivity::class.java.name
    }

    private val viewModel: SingleEventViewModel by lazy {
        ViewModelProvider(this, SingleEventModelFactory())[SingleEventViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_event)

        initViewModel()
    }

    private fun initViewModel() {
        viewModel.showSnackBar.observe(this, {
            Log.d(TAG, "updating snackbar")
            showSnackBar()
        })
    }

    private fun showSnackBar() {
        val container = findViewById<ConstraintLayout>(R.id.cl_container)
        val snackBar = Snackbar.make(container, "This is main activity", Snackbar.LENGTH_LONG)

        snackBar
            .setActionTextColor(resources.getColor(android.R.color.holo_red_light))
            .setAction("CLOSE ") {
                snackBar.dismiss()
            }

        snackBar.show()

    }
}