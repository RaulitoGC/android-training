package com.poc.flowchannel.examples.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.poc.flowchannel.R
import com.poc.flowchannel.databinding.ActivityChannelsReceiversBinding
import com.poc.flowchannel.databinding.ActivityFlowBinding
import com.poc.flowchannel.examples.viewmodel.FlowViewModel


class FlowActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFlowBinding
    private val flowViewModel: FlowViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFlowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindObservers()
        bindListeners()
    }

    private fun bindObservers() {
        flowViewModel.flowValues.observe(this, Observer {
            binding.flowValues.text = it
        })
        flowViewModel.callbackFlowValues.observe(this, Observer {
            binding.callbackFlowValues.text = it
        })
    }

    private fun bindListeners() {
        binding.initFlows.setOnClickListener { flowViewModel.initEmissions() }
    }

}