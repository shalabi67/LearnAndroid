package com.androidlibrary.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase.CursorFactory
import android.database.sqlite.SQLiteDatabase
import android.database.SQLException
import com.androidlibrary.Debugger
import java.util.*


/**
 * Created by mohammad on 11/21/2017.
 */
abstract class Database {

    protected var dbHelper: SqliteHelper

    var databaseName = ""

    var databaseVersion = 1


    internal var tables: MutableList<Table> = ArrayList<Table>()
    internal var upgradeTables: MutableList<Table> = ArrayList<Table>()

    protected constructor(context: Context, name: String, factory: CursorFactory?,
                          version: Int) {
        var fullPath = name

        /*
        if (Debugger.canLOG) {
            val file = context.getExternalFilesDir(null)
            if (file != null) {
                fullPath = "${file.absolutePath}/${name}"
            }
        }*/
        this.databaseName = fullPath
        this.databaseVersion = version

        dbHelper = SqliteHelper(this, context, factory, version)

    }

    fun addTable(table: Table?) {
        if (table == null)
            return
        tables.add(table)
    }

    fun create(sqliteDatabase: SQLiteDatabase?)  {
        if(sqliteDatabase == null)
            return
        val databaseOperations = DatabaseOperations(sqliteDatabase)
        for (table in tables) {
            table.create(databaseOperations)
        }

        initDatabaseData(databaseOperations)
    }
    abstract fun initDatabaseData( databaseOperations : DatabaseOperations)

    fun addUpgradeTable(table: Table?) {
        if (table == null)
            return
        upgradeTables.add(table)
    }

    fun upgrade(sqliteDatabase: SQLiteDatabase?) {
        if(sqliteDatabase == null)
            return
        val databaseOperations = DatabaseOperations(sqliteDatabase)
        for (table in upgradeTables) {
            table.upgrade()
        }

        initDatabaseUpgradeData(databaseOperations)
    }
    abstract fun initDatabaseUpgradeData( databaseOperations : DatabaseOperations)


    fun open() : DatabaseOperations {
        return DatabaseOperations(dbHelper.getWritableDatabase())
    }


    fun openReadable() : DatabaseOperations {
        return DatabaseOperations(dbHelper.getReadableDatabase())
    }

}
