package com.poc.flowchannel.examples.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.poc.flowchannel.R
import com.poc.flowchannel.databinding.ActivityChannelsReceiversBinding
import com.poc.flowchannel.examples.viewmodel.ChannelReceiversViewModel

class ChannelReceiversActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChannelsReceiversBinding
    private val viewModel: ChannelReceiversViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChannelsReceiversBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_channels_receivers)

        bindObservers()
        bindListeners()
    }

    private fun bindObservers() {
        viewModel.rendezvousChannelReceiver1.observe(this, Observer {
            binding.rendezvousReceiver1Values.text = it
        })
        viewModel.rendezvousChannelReceiver2.observe(this, Observer {
            binding.rendezvousReceiver2Values.text = it
        })

        viewModel.unlimitedChannelReceiver1.observe(this, Observer {
            binding.unlimitedReceiver1Values.text = it
        })
        viewModel.unlimitedChannelReceiver2.observe(this, Observer {
            binding.unlimitedReceiver2Values.text = it
        })

        viewModel.conflatedChannelReceiver1.observe(this, Observer {
            binding.conflatedReceiver1Values.text = it
        })
        viewModel.conflatedChannelReceiver2.observe(this, Observer {
            binding.conflatedReceiver2Values.text = it
        })
    }

    private fun bindListeners() {
        binding.initChannelEmission.setOnClickListener {
            viewModel.initEmission()
        }
    }

}