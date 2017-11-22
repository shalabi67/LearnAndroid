package com.androidlibrary

import android.util.Log
import android.view.View

/**
 * Created by mohammad on 11/21/2017.
 */
class Debugger {
    companion object {
        val canLOG = true
    }

    fun showViewPosition(TAG: String, view: View) {
        Log.i(TAG, "View left:${view.getLeft()} top:${view.getTop()}  " +
                "right:${view.getRight()} bottom:${view.getBottom()}")
    }
}