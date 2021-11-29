package com.poc.flowchannel.examples.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.poc.flowchannel.databinding.ActivityBroadcastChannelsBinding
import com.poc.flowchannel.examples.viewmodel.BroadcastChannelViewModel

class BroadcastChannelActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBroadcastChannelsBinding
    private val viewModel: BroadcastChannelViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBroadcastChannelsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindObservers()
        bindListeners()
    }

    private fun bindObservers() {
        viewModel.broadcastChannelReceiver1.observe(this, Observer {
            binding.openSuscriptionReceiver1Values.text = it
        })
        viewModel.broadcastChannelReceiver2.observe(this, Observer {
            binding.openSuscriptionReceiver2Values.text = it
        })

        viewModel.asFlowBroadcastChannelReceiver1.observe(this, Observer {
            binding.asFlowReceiver1Values.text = it
        })
        viewModel.asFlowBroadcastChannelReceiver2.observe(this, Observer {
            binding.asFlowReceiver2Values.text = it
        })
    }

    private fun bindListeners() {
        binding.initChannelEmission.setOnClickListener {
            viewModel.initEmissions()
        }
    }
}
