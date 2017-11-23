package com.learn.notekeeper.data.note

import android.app.Activity
import android.content.CursorLoader
import android.content.Loader
import android.database.Cursor
import android.os.Bundle
import android.util.Log
import com.androidlibrary.database.DatabaseOperations
import com.androidlibrary.ui.loader.DataFeeder
import com.androidlibrary.ui.loader.DataLoader
import com.learn.notekeeper.data.course.Courses
import com.learn.notekeeper.data.course.CoursesLoader

/**
 * Created by mohammad on 11/23/2017.
 */
class NotesLoader(dataFeeder: DataFeeder, databaseOperations: DatabaseOperations)
    : DataLoader(dataFeeder, databaseOperations) {

    companion object {
        val TAG = NotesLoader::class.java.name
        val ID = 3
    }



    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        Log.d(NoteLoader.TAG, "onCreateLoader")
        val activity = dataFeeder as Activity
        return object : CursorLoader(activity.applicationContext) {
            override fun loadInBackground(): Cursor {
                Log.i(NotesLoader.TAG, "loadInBackground")
                val cursor = Notes.getNotesCursor(databaseOperations)
                return cursor
            }
        }
    }

    override fun onLoadFinished(loader: Loader<Cursor>?, data: Cursor?) {
        Log.d(NotesLoader.TAG, "onLoadFinished")
        if(data == null) {
            Log.e(NotesLoader.TAG, "onLoadFinished has null cursor")
            return
        }

        dataFeeder.fillData(NotesLoader.ID, data)
        cursor = data
    }

    override fun onLoaderReset(loader: Loader<Cursor>?) {
        Log.d(NotesLoader.TAG, "onLoaderReset")
        cursor.close()
    }

}