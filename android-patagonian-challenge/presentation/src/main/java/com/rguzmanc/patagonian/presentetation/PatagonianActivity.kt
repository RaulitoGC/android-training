package com.rguzmanc.patagonian.presentetation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.rguzmanc.patagonian.presentetation.databinding.ActivityPatagonianBinding
import com.rguzmanc.patagonian.presentetation.di.CompositionRoot
import com.rguzmanc.patagonian.presentetation.di.PatagonianViewModelProvider
import com.rguzmanc.patagonian.presentetation.sensor.DefaultPatagonianSensor
import com.rguzmanc.patagonian.presentetation.sensor.PatagonianSensor
import kotlinx.coroutines.flow.collect

class PatagonianActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "PatagonianActivity"

        fun getIntent(context: Context) = Intent(context, PatagonianActivity::class.java)
    }

    private lateinit var binding: ActivityPatagonianBinding
    private val patagonianViewModel: PatagonianViewModel by viewModels {
        PatagonianViewModelProvider(CompositionRoot(this@PatagonianActivity))
    }
    private lateinit var patagonianSensor: PatagonianSensor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPatagonianBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpSensor()
        initViewModel()
        initSensorListener()
    }

    private fun setUpSensor() {
        patagonianSensor = DefaultPatagonianSensor(this@PatagonianActivity)
        lifecycle.addObserver(patagonianSensor)

        if (patagonianSensor.isAvailable().not()) {
            Toast.makeText(
                this,
                getString(R.string.text_gyroscope_is_not_available),
                Toast.LENGTH_LONG
            ).show()
            patagonianSensor.unRegisterListener()
        }
    }

    private fun initSensorListener() {
        patagonianSensor.listener = object : PatagonianSensor.Listener {
            override fun onDataChange(dz: Float) {
                patagonianViewModel.getDimension(dz)
            }
        }
    }

    private fun initViewModel() {
        lifecycleScope.launchWhenStarted {
            patagonianViewModel.sessionCount.collect { count ->
                binding.txtSessionCounter.text =
                    getString(R.string.text_session_count, count.toString())
            }
        }

        lifecycleScope.launchWhenStarted {
            patagonianViewModel.textSize.collect { dimensionId ->
                binding.txtSessionCounter.textSize = resources.getDimension(dimensionId)
            }
        }
    }
}