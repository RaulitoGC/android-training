package com.poc.flowchannel

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.poc.flowchannel.app.home.ui.HomeActivity
import com.poc.flowchannel.databinding.ActivityMainBinding
import com.poc.flowchannel.examples.ui.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            buttonFlow.setOnClickListener {
                startActivity(Intent(this@MainActivity, FlowActivity::class.java))
            }
            buttonChannel.setOnClickListener {
                startActivity(Intent(this@MainActivity, ChannelActivity::class.java))
            }
            buttonStateFlow.setOnClickListener {
                startActivity(Intent(this@MainActivity, StateFlowActivity::class.java))
            }
            buttonChannelReceivers.setOnClickListener {
                startActivity(Intent(this@MainActivity, ChannelReceiversActivity::class.java))
            }
            buttonBroadcastChannel.setOnClickListener {
                startActivity(Intent(this@MainActivity, BroadcastChannelActivity::class.java))
            }

            buttonGoToApp.setOnClickListener {
                startActivity(Intent(this@MainActivity, HomeActivity::class.java))
            }
        }
    }
}
