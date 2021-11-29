package com.poc.flowchannel.app.home.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.poc.flowchannel.app.detail.ui.TweetDetailActivity
import com.poc.flowchannel.app.home.model.ScreenTweet
import com.poc.flowchannel.app.home.ui.adapter.TweetListAdapter
import com.poc.flowchannel.app.home.ui.adapter.TweetListCallback
import com.poc.flowchannel.app.home.viewmodel.HomeViewModel
import com.poc.flowchannel.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity(), TweetListCallback {

    private lateinit var binding: ActivityHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()

    private val tweetsAdapter = TweetListAdapter(tweetListCallback = this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configureRecycler()
        bindObservers()
    }

    override fun onTweetSelected(screenTweet: ScreenTweet) {
        startActivity(TweetDetailActivity.get(this, screenTweet))
    }

    private fun configureRecycler() = with(binding){
        tweetList.layoutManager = LinearLayoutManager(this@HomeActivity)
        tweetList.adapter = tweetsAdapter
    }

    private fun bindObservers() {
        homeViewModel.tweets.observe(this, Observer {
            tweetsAdapter.items = it
            tweetsAdapter.notifyDataSetChanged()
        })

        homeViewModel.unreadMessages.observe(this, Observer {
            binding.unreadMessagesText.text = it.toString()
        })
    }

}