package com.androidlibrary.database

import android.database.Cursor
import android.provider.BaseColumns
import com.androidlibrary.database.column.Column

/**
 * Created by mohammad on 11/21/2017.
 */
abstract class Table : BaseColumns {
    protected lateinit var columns: List<Column>

    protected var tableName = ""
    fun getName() : String = tableName
    fun getColumnsString(): String {
        var s = ""
        for (c in columns) {
            s += c.toString() + ","
        }
        s = s.substring(0, s.length - 2)
        return s
    }

    fun create(database: DatabaseOperations) {
        val sql = "CREATE TABLE ${tableName}(${getColumnsString()})"
        database.executeSQL(sql)
    }

    fun upgrade() {

    }

    fun <T : Data> fill(cursor: Cursor): List<T> {
        val list = ArrayList<T>()
        cursor.moveToFirst()
        while (!cursor.isAfterLast()) {
            val table : T = read(cursor)
            list.add(table)
            cursor.moveToNext()
        }
        // Make sure to close the cursor
        cursor.close()

        return list
    }

    abstract fun <T : Data> read(cursor: Cursor): T


}
