package com.learn.notekeeper.data.note

import android.annotation.SuppressLint
import android.app.Activity
import android.app.LoaderManager
import android.content.Context
import android.content.CursorLoader
import android.content.Loader
import android.database.Cursor
import android.os.Bundle
import android.util.Log
import com.androidlibrary.database.DatabaseOperations
import com.androidlibrary.ui.loader.DataFeeder
import com.androidlibrary.ui.loader.DataLoader

/**
 * Created by mohammad on 11/23/2017.
 */
class NoteLoader(val noteId : Long, dataFeeder: DataFeeder, databaseOperations: DatabaseOperations)
    : DataLoader(dataFeeder, databaseOperations) {
    companion object {
        val TAG = NoteLoader::class.java.name
        val ID = 1
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        Log.d(TAG, "onCreateLoader")
        val activity = dataFeeder as Activity
        return object : CursorLoader(activity.applicationContext) {
            override fun loadInBackground(): Cursor {
                Log.i(TAG, "loadInBackground")
                val cursor = Notes.getNotesByIdCursor(noteId, databaseOperations)
                cursor.moveToFirst()
                return cursor
            }
        }
        //return NotesCursorLoader(activity.applicationContext)
    }


    override fun onLoadFinished(loader: Loader<Cursor>?, data: Cursor?) {
        Log.d(TAG, "onLoadFinished")
        if(data == null) {
            Log.e(TAG, "onLoadFinished has null cursor")
            return
        }

        dataFeeder.fillData(NoteLoader.ID, data)

        data.close()
    }

    override fun onLoaderReset(loader: Loader<Cursor>?) {
        Log.d(TAG, "onLoaderReset")
    }
}