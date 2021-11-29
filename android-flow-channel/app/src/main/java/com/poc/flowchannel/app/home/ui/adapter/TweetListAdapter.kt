package com.poc.flowchannel.app.home.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.poc.flowchannel.R
import com.poc.flowchannel.app.home.model.ScreenTweet
import com.poc.flowchannel.databinding.TweetItemLayoutBinding

class TweetListAdapter(
    var items: List<ScreenTweet>? = listOf(),
    private val tweetListCallback: TweetListCallback
) : RecyclerView.Adapter<TweetListViewHolder>() {

    private lateinit var inflater: LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetListViewHolder {
        val view = TweetItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        view.root.setOnClickListener {
            tweetListCallback.onTweetSelected(view.root.getTag(R.id.list_tweet) as ScreenTweet)
        }

        return TweetListViewHolder(view)
    }

    override fun getItemCount(): Int = items?.size ?: 0

    override fun onBindViewHolder(holder: TweetListViewHolder, position: Int) =
        holder.bind(items?.get(position)!!)
}

class TweetListViewHolder(private val binding: TweetItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(screenTweet: ScreenTweet) = with(binding){
        firstNameLetterText.text = screenTweet.username.first().toUpperCase().toString()
        usernameText.text = screenTweet.username
        tweetText.text = screenTweet.tweet
        repliesText.text = screenTweet.responsesQuantity.toString()
        retweetsText.text = screenTweet.rtsQuantity.toString()
        favsText.text = screenTweet.favoritesQuantity.toString()

        itemView.setTag(R.id.list_tweet, screenTweet)
    }

}

interface TweetListCallback {
    fun onTweetSelected(screenTweet: ScreenTweet)
}