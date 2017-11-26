package com.learn.socialmedia.framework

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import java.io.ByteArrayOutputStream

/**
 * Created by mohammad on 11/26/2017.
 */
class File {
    companion object {
        fun getUriFromUrl(context : Context, filePath: String): Uri? {
            try {
                val options = BitmapFactory.Options()
                options.inPreferredConfig = Bitmap.Config.ARGB_8888
                val inImage = BitmapFactory.decodeFile(filePath, options)

                val bytes = ByteArrayOutputStream()
                inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
                val path = MediaStore.Images.Media.insertImage(
                        context.contentResolver, inImage, "Title", null)
                return Uri.parse(path)
            } catch (e: Exception) {
                // TODO: handle exception
                e.printStackTrace()

            }

            return null
        }
    }
}