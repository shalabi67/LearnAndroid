package com.androidlibrary.database

import android.database.Cursor
import android.provider.BaseColumns
import android.util.Log
import com.androidlibrary.database.column.Column
import com.androidlibrary.database.column.Columns
import com.androidlibrary.database.column.TableColumn

/**
 * Created by mohammad on 11/21/2017.
 */
abstract class Table : BaseColumns {
    companion object {
        val TAG = Table::class.java.name
    }
    lateinit var columns: Columns
    fun getTableColumns() : Array<String>  = columns.getColumnsString({col -> col.getColumnNameForQuery()}).split(",").toTypedArray()

    protected var tableName = ""
    fun getName() : String = tableName

    open protected fun getCreateSql() : String = "CREATE TABLE ${tableName}(${columns.getColumnsString()})"
    fun create(database: DatabaseOperations) {
        val sql = getCreateSql()
        database.executeSQL(sql)
    }

    fun <T : Data> fill(cursor: Cursor): MutableList<T> {
        val list = ArrayList<T>()
        while (cursor.moveToNext()) {
            val table : T = read(cursor)
            list.add(table)
        }
        // Make sure to close the cursor
        cursor.close()

        return list
    }

    abstract fun <T : Data> read(cursor: Cursor): T


}
