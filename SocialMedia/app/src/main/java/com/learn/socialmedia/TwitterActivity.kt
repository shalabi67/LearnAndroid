package com.learn.socialmedia

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.twitter.sdk.android.tweetcomposer.ComposerActivity
import android.content.Intent
import android.net.Uri
import android.os.Environment
import com.twitter.sdk.android.core.TwitterCore
import com.twitter.sdk.android.core.TwitterSession

import android.os.Environment.DIRECTORY_PICTURES
import android.os.Environment.getExternalStoragePublicDirectory
import com.google.android.exoplayer.util.UriUtil
import java.io.File
import android.support.v4.content.FileProvider




class TwitterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_twitter)

        val root = File(Environment.getExternalStorageDirectory(), "Android/data/com.learn.socialmedia/files/images")
        val file = File( root,"project.gif")
        val imageUri = FileProvider.getUriForFile(this,
                "${BuildConfig.APPLICATION_ID}.fileprovider",
                file)
        val session = TwitterCore.getInstance().sessionManager
                .activeSession

        val intent = ComposerActivity.Builder(this)
                .session(session)
                .image(imageUri)
                .text("eBardy greeting card")
                .hashtags("#eBardy")
                .createIntent()
        startActivity(intent)
    }
}
