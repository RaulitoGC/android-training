package com.poc.flowchannel.app.detail.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.poc.flowchannel.R
import com.poc.flowchannel.app.detail.viewmodel.TweetDetailViewModel
import com.poc.flowchannel.app.home.model.ScreenTweet
import com.poc.flowchannel.databinding.ActivityTweetDetailBinding


class TweetDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTweetDetailBinding
    private val viewModel: TweetDetailViewModel by viewModels()

    companion object {

        private const val SCREEN_TWEET_KEY = "SCREEN_TWEET_KEY"

        fun get(context: Context, screenTweet: ScreenTweet): Intent {
            val bundle = Bundle()
            bundle.putParcelable(SCREEN_TWEET_KEY, screenTweet)
            val intent = Intent(context, TweetDetailActivity::class.java)
            intent.putExtras(bundle)
            return intent
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTweetDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fillData()
        bindObservers()
    }

    private fun fillData() = with(binding){
        val screenTweet = intent.extras?.get(SCREEN_TWEET_KEY) as ScreenTweet
        firstNameLetterText.text = screenTweet.username.first().toUpperCase().toString()
        usernameText.text = screenTweet.username
        tweetText.text = screenTweet.tweet
        repliesText.text = screenTweet.responsesQuantity.toString()
        retweetsText.text = screenTweet.rtsQuantity.toString()
        favsText.text = screenTweet.favoritesQuantity.toString()

        viewModel.getTweetInteractions(screenTweet.id)
    }

    private fun bindObservers() {
        viewModel.unreadMessages.observe(this, Observer {
            binding.unreadMessagesText.text = it.toString()
        })

        viewModel.tweetInteractions.observe(this, Observer {
            binding.repliesText.text = it.responsesQuantity.toString()
            binding.retweetsText.text = it.rtsQuantity.toString()
            binding.favsText.text = it.favoritesQuantity.toString()
        })
    }


}