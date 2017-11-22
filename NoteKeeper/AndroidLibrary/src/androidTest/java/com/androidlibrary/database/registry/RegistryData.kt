package com.androidlibrary.database.registry

import android.content.ContentValues
import com.androidlibrary.database.Data

/**
 * Created by mohammad on 11/22/2017.
 */
class RegistryData : Data {
    override fun getContentValues(): ContentValues {
        val contentValues = ContentValues()
        contentValues.put(Registry.KEY, key)
        contentValues.put(Registry.VALUE, value)
        contentValues.put(Registry.TYPE, type)

        return contentValues
    }

    var key = ""
    var value = ""
    var type = 0
}