package com.learn.notekeeper.data.course

import android.app.Activity
import android.content.CursorLoader
import android.content.Loader
import android.database.Cursor
import android.os.Bundle
import android.util.Log
import com.androidlibrary.database.DatabaseOperations
import com.androidlibrary.ui.loader.DataFeeder
import com.androidlibrary.ui.loader.DataLoader
import com.learn.notekeeper.data.note.NoteLoader
import com.learn.notekeeper.data.note.Notes

/**
 * Created by mohammad on 11/23/2017.
 */
class CoursesLoader (dataFeeder: DataFeeder, databaseOperations: DatabaseOperations)
    : DataLoader(dataFeeder, databaseOperations) {
    companion object {
        val TAG = CoursesLoader::class.java.name
        val ID = 2
    }
    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        Log.d(NoteLoader.TAG, "onCreateLoader")
        val activity = dataFeeder as Activity
        return object : CursorLoader(activity.applicationContext) {
            override fun loadInBackground(): Cursor {
                Log.i(NoteLoader.TAG, "loadInBackground")
                val cursor = Courses.getCoursesCursor(databaseOperations)
                return cursor
            }
        }
    }

    override fun onLoadFinished(loader: Loader<Cursor>?, data: Cursor?) {
        Log.d(TAG, "onLoadFinished")
        if(data == null) {
            Log.e(NoteLoader.TAG, "onLoadFinished has null cursor")
            return
        }

        dataFeeder.fillData(CoursesLoader.ID, data)
        cursor = data

    }

    override fun onLoaderReset(loader: Loader<Cursor>?) {
        Log.d(NoteLoader.TAG, "onLoaderReset")
        cursor.close()
    }
}