package com.androidlibrary.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by mohammad on 11/21/2017.
 */
class SqliteHelper(val database : Database, context : Context, factory : SQLiteDatabase.CursorFactory?,  version : Int) :
        SQLiteOpenHelper(context, database.databaseName, factory, version) {
    constructor(database : Database, context : Context) : this(database, context, null, database.databaseVersion)  {

    }
    override fun onCreate(db: SQLiteDatabase) {
        database.create(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        database.upgrade(db, oldVersion, newVersion)
    }


}