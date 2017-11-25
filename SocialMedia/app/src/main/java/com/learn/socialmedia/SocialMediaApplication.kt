package com.learn.socialmedia

import android.app.Application
import android.util.Log
import com.facebook.FacebookSdk
import com.twitter.sdk.android.core.Twitter
import com.twitter.sdk.android.core.TwitterAuthConfig
import com.twitter.sdk.android.core.DefaultLogger
import com.twitter.sdk.android.core.TwitterConfig



/**
 * Created by mohammad on 11/24/2017.
 */
class SocialMediaApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        val config = TwitterConfig.Builder(this)
                .logger(DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(TwitterAuthConfig(getString(R.string.CONSUMER_KEY),
                        getString(R.string.CONSUMER_SECRET)))
                .debug(true)
                .build()
        Twitter.initialize(config)

        FacebookSdk.sdkInitialize(applicationContext);
    }
}