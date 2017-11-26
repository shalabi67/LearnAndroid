package com.learn.socialmedia.framework

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.twitter.sdk.android.tweetcomposer.TweetUploadService

class TwitterResultReceiver : BroadcastReceiver() {
    companion object {
        val TAG = TwitterResultReceiver::class.java.name
    }
    override fun onReceive(context: Context, intent: Intent) {
        if (TweetUploadService.UPLOAD_SUCCESS.equals(intent.getAction())) {
            Log.d(TAG, "twitted success")
            // success
            //val tweetId = intentExtras.getLong(TweetUploadService.EXTRA_TWEET_ID);
        } else if (TweetUploadService.UPLOAD_FAILURE.equals(intent.getAction())) {
            Log.d(TAG, "twitted faild")
            // failure
            //val retryIntent = intentExtras.getParcelable(TweetUploadService.EXTRA_RETRY_INTENT);
        } else if (TweetUploadService.TWEET_COMPOSE_CANCEL.equals(intent.getAction())) {
            Log.d(TAG, "twitted canceled")
            // cancel
        }
    }
}
