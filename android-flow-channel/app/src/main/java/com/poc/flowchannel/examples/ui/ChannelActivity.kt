package com.poc.flowchannel.examples.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.poc.flowchannel.databinding.ActivityChannelBinding
import com.poc.flowchannel.examples.viewmodel.ChannelViewModel

class ChannelActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChannelBinding
    private val channelViewModel: ChannelViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChannelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindObservers()
        bindListeners()
    }

    private fun bindObservers() {
        channelViewModel.bufferedValue.observe(this, Observer {
            binding.bufferedValues.text = it
        })
        channelViewModel.conflatedValue.observe(this, Observer {
            binding.conflatedValues.text = it
        })
        channelViewModel.otherValue.observe(this, Observer {
            binding.otherValues.text = it
        })
        channelViewModel.rendezvousValue.observe(this, Observer {
            binding.rendezvousValues.text = it
        })
        channelViewModel.unlimitedValue.observe(this, Observer {
            binding.unlimitedValues.text = it
        })
    }

    private fun bindListeners() {
        binding.initChannels.setOnClickListener {
            channelViewModel.initEmissions()

            channelViewModel.timerValues().observe(this, Observer {
                binding.normalEmissionValues.text = it
            })
        }

        with(binding) {
            receiveRendezvous.setOnClickListener { channelViewModel.receiveRendezvous() }
            receiveBuffered.setOnClickListener { channelViewModel.receiveBuffered() }
            receiveConflated.setOnClickListener { channelViewModel.receiveConflated() }
            receiveOther.setOnClickListener { channelViewModel.receiveOther() }
            receiveUnlimited.setOnClickListener { channelViewModel.receiveUnlimited() }
        }
    }
}
