package com.learn.android.vectordrawablesvg

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.graphics.drawable.Animatable
import android.graphics.drawable.Drawable
import android.widget.ImageView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mIcDownloadAnimator = findViewById<ImageView>(R.id.imageViewDownload)
        val drawable = mIcDownloadAnimator.getDrawable()
        if (drawable is Animatable) {
            (drawable as Animatable).start()
        }

    }
}
