package com.androidlibrary.ui.loader

import android.database.Cursor

/**
 * Created by mohammad on 11/23/2017.
 */
interface DataFeeder {
    fun fillData(cursor : Cursor)
}