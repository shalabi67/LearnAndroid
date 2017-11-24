package com.androidlibrary.database

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

/**
 * Created by mohammad on 11/22/2017.
 */
class DatabaseOperations(val database: SQLiteDatabase) {


    fun close() {
        database.close()
    }


    fun executeSQL(sql: String) {
        database.execSQL(sql)
    }

    fun <T : Data> insert(table : Table, data : T) : Long {
        return insert(table.getName(), table.columns.getInsertionColumns(), data.getContentValues())
    }
    fun insert(table: String, nullColumnHack: String, values: ContentValues): Long {
        return database.insert(table, nullColumnHack, values)
    }

    fun query(table : Table, selection: String? = null, selectionArgs: Array<String>? = null, groupBy: String? = null, having: String? = null, orderBy: String? = null) : Cursor {
        return database.query(table.getName(), table.getTableColumns(), selection, selectionArgs, groupBy, having, orderBy)
    }
    fun query(table: String, columns: Array<String>, selection: String, selectionArgs: Array<String>, groupBy: String, having: String, orderBy: String): Cursor {
        return database.query(table, columns, selection, selectionArgs, groupBy, having, orderBy)
    }

    fun <T : Data> update(table : Table, data : T) : Int {
        return update(table.getName(), data.getContentValues(), "${table.columns.primaryKey!!.columnName} = ?", arrayOf("${data.getPrimaryKey()}") )
    }
    fun update(table: String, values: ContentValues, whereClause: String, whereArgs: Array<String>): Int {
        return database.update(table, values, whereClause, whereArgs)

    }

    fun delete(table: String, whereClause: String, whereArgs: Array<String>): Int {
        return database.delete(table, whereClause, whereArgs)
    }

}