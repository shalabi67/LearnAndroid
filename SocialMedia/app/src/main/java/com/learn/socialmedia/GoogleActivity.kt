package com.learn.socialmedia

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore.Images
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import com.google.android.gms.plus.PlusShare
import java.io.ByteArrayOutputStream
import java.io.File


class GoogleActivity : AppCompatActivity() {
    companion object {
        val TAG = GoogleActivity::class.java.name
        val REQ_SELECT_PHOTO = 1
        val REQ_START_SHARE = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google)

        val shareButton = findViewById<Button>(R.id.share_button)
        shareButton.setOnClickListener {
            // Launch the Google+ share dialog with attribution to your app.
            val shareIntent = PlusShare.Builder(this@GoogleActivity)
                    .setType("text/plain")
                    .setText("eBardy Animated Greeting Cards.")
                    .setContentUrl(Uri.parse("https://play.google.com/store/apps/details?id=com.ne3am.avatarproject"))
                    .intent

            startActivityForResult(shareIntent, REQ_SELECT_PHOTO)
        }

        val root = File(Environment.getExternalStorageDirectory(), "Android/data/com.learn.socialmedia/files/images")
        val file = File( root,"project.gif")

        if (isPackageInstalled("com.google.android.apps.plus", this)) {
            Log.d(TAG, "isPackageInstalled = true")
        }

        val shareMediaButton = findViewById<Button>(R.id.share_media)
        val imageUri = com.learn.socialmedia.framework.File.getUriFromUrl(this, "/storage/sdcard1/thanks.jpg")
        shareMediaButton.setOnClickListener {
            val share = PlusShare.Builder(this@GoogleActivity)
                    .setText("hello everyone!")
                    .addStream(imageUri)
                    .setType("image/jpeg") //mime)
                    .intent
            startActivityForResult(share, REQ_START_SHARE)
        }

        val pickButton = findViewById<Button>(R.id.pick_media)
        pickButton.setOnClickListener {
            val photoPicker = Intent(Intent.ACTION_PICK)
            photoPicker.type = "video/*, image/*"

            startActivityForResult(photoPicker, REQ_SELECT_PHOTO)
        }
    }

    lateinit var selectedImage : Uri
    lateinit var mime : String
    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent) {
        super.onActivityResult(requestCode, resultCode, intent)

        if (requestCode == REQ_SELECT_PHOTO) {
            if (resultCode == Activity.RESULT_OK) {
                selectedImage = intent.data
                val cr = this.contentResolver
                mime = cr.getType(selectedImage!!)


            }
        } else if(requestCode == REQ_START_SHARE) {

        }
    }

    private fun isPackageInstalled(packageName: String, context: Context): Boolean {
        val pm = context.getPackageManager()
        try {
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
            return true
        } catch (e: PackageManager.NameNotFoundException) {
            return false
        }

    }

    fun getUriFromUrl(filePath: String): Uri? {
        try {
            val options = BitmapFactory.Options()
            options.inPreferredConfig = Bitmap.Config.ARGB_8888
            val inImage = BitmapFactory.decodeFile(filePath, options)

            val bytes = ByteArrayOutputStream()
            inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
            val path = Images.Media.insertImage(
                    this.contentResolver, inImage, "Title", null)
            return Uri.parse(path)
        } catch (e: Exception) {
            // TODO: handle exception
            e.printStackTrace()

        }

        return null
    }
}
