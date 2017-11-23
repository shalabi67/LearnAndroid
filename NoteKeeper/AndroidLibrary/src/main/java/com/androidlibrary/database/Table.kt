package com.androidlibrary.database

import android.database.Cursor
import android.provider.BaseColumns
import android.util.Log
import com.androidlibrary.database.column.Column
import com.androidlibrary.database.column.TableColumn

/**
 * Created by mohammad on 11/21/2017.
 */
abstract class Table : BaseColumns {
    companion object {
        val TAG = Table::class.java.name
    }
    protected lateinit var columns: List<Column>
    fun getTableColumns() : Array<String>  = getColumnsString({col -> col.getColumnNameForQuery()}).split(",").toTypedArray()

    protected var tableName = ""
    fun getName() : String = tableName
    fun getColumnsString(function : (column : Column) -> String = {column -> column.toString()} ): String {
        var s = ""
        for (c in columns) {
            //s += c.toString() + ","
            s += function(c) + ","
        }
        s = s.substring(0, s.length - 1)
        return s
    }
    //open fun getColumnNameForQuery(col : Column) : String  =  col.columnName

    open protected fun getCreateSql() : String = "CREATE TABLE ${tableName}(${getColumnsString()})"
    fun create(database: DatabaseOperations) {
        val sql = getCreateSql()
        database.executeSQL(sql)
    }

    fun upgrade() {

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

    open fun getColumnByName(columnName : String, table : Table? = null) : Column {
        val list = columns.filter { col -> col.columnName.equals(columnName) }
        if(list.isEmpty()) {
            Log.e(TAG, "getColumnByName could not find column : $columnName")
            return Column.getInvalidColumn()
        } else if(list.size > 1) {
            Log.e(TAG, "getColumnByName found more than one column with the same name : $columnName")
        }

        return list[0]
    }

    abstract fun <T : Data> read(cursor: Cursor): T


}
