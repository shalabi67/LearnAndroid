package com.learn.socialmedia

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import com.learn.socialmedia.framework.File
import com.twitter.sdk.android.core.TwitterCore
import com.twitter.sdk.android.tweetcomposer.ComposerActivity
import java.io.ByteArrayOutputStream


class TwitterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_twitter)

        ///storage/emulated/0/Android/data/com.learn.socialmedia/files/images/project.gif
        //val root = java.io.File(Environment.getExternalStorageDirectory(), "Android/data/com.learn.socialmedia/files/images")
        //val file = java.io.File(root, "project.gif")
       /* val imageUri = FileProvider.getUriForFile(this,
                "${BuildConfig.APPLICATION_ID}.fileprovider",
                file)*/
        val session = TwitterCore.getInstance().sessionManager
                .activeSession

        val imageUri = File.getUriFromUrl(this, "/storage/sdcard1/thanks.jpg")
        //val imageUri = File.getUriFromUrl(this, file.absolutePath)
        val intent = ComposerActivity.Builder(this)
                .session(session)
                .image(imageUri)
                .text("eBardy greeting card")
                .hashtags("#eBardy")
                .createIntent()
        startActivity(intent)
    }

    fun getUriFromUrl(filePath: String): Uri? {
        try {
            val options = BitmapFactory.Options()
            options.inPreferredConfig = Bitmap.Config.ARGB_8888
            val inImage = BitmapFactory.decodeFile(filePath, options)

            val bytes = ByteArrayOutputStream()
            inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
            val path = MediaStore.Images.Media.insertImage(
                    this.contentResolver, inImage, "Title", null)
            return Uri.parse(path)
        } catch (e: Exception) {
            // TODO: handle exception
            e.printStackTrace()

        }

        return null
    }
}
