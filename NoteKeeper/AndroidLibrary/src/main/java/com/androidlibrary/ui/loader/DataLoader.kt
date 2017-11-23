package com.androidlibrary.ui.loader

import android.app.LoaderManager
import android.database.Cursor
import com.androidlibrary.database.DatabaseOperations

/**
 * Created by mohammad on 11/23/2017.
 */
abstract class DataLoader(val dataFeeder: DataFeeder, val databaseOperations: DatabaseOperations) : LoaderManager.LoaderCallbacks<Cursor> {

}