package com.androidlibrary.database

import android.content.ContentValues

/**
 * Created by mohammad on 11/22/2017.
 */
interface Data {
    fun getContentValues() : ContentValues
    fun getPrimaryKey() : String
}