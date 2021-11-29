package com.poc.flowchannel.examples.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.Observer
import com.poc.flowchannel.R
import com.poc.flowchannel.databinding.ActivityMainBinding
import com.poc.flowchannel.databinding.ActivityStateflowBinding
import com.poc.flowchannel.examples.viewmodel.StateFlowViewModel

class StateFlowActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStateflowBinding
    private val viewModel: StateFlowViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStateflowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindListeners()
    }

    private fun bindListeners() {
        binding.initFlows.setOnClickListener {
            viewModel.initEmissions()
        }

        binding.addReceiver.setOnClickListener {
            val textView = createTextView()
            viewModel.addStateFlowCollector().observe(this, Observer {
                textView.text = "Value: $it"
            })
        }
    }

    private fun createTextView(): AppCompatTextView {
        val textView = AppCompatTextView(this)
        binding.stateFlowValuesContainer.addView(textView)
        return textView
    }

}