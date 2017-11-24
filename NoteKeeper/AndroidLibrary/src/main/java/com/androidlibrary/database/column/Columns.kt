package com.androidlibrary.database.column

import android.util.Log
import com.androidlibrary.database.Table

/**
 * Created by mohammad on 11/24/2017.
 */
class Columns : ArrayList<Column>() {
    companion object {
        val TAG = Columns::class.java.name
    }
    var primaryKey : Column? = null
    var autoincrement : Column? = null
    override fun add(column: Column): Boolean {
        if(primaryKey == null && column.isPrimaryKey()) {
            primaryKey = column
        }
        if(autoincrement == null && column.isAutoIncrement()) {
            autoincrement = column
        }
        return super.add(column)
    }
    fun getInsertionColumns() : String {
        return this.filter {column -> column != autoincrement}.map { column -> column.columnName }.joinToString(",")
    }

    fun getColumnsString(function : (column : Column) -> String = {column -> column.toString()} ): String {
        var s = ""
        for (c in this) {
            //s += c.toString() + ","
            s += function(c) + ","
        }
        s = s.substring(0, s.length - 1)
        return s
    }

    fun getColumnByName(columnName : String, table : Table?) : Column {
        val columnList = this.filter({ col -> isRequestedColumn(col, columnName, table)})
        if(columnList.isEmpty()) {
            Log.e(TAG, "getColumnByName return empty list for $columnName and ${table?.getName()}")
            if(table == null)
                return Column.getInvalidColumn()
            return TableColumn(table, Column.getInvalidColumn())
        }

        return columnList[0]
    }
    private fun isRequestedColumn(column : Column, columnName : String, table : Table?) : Boolean {
        val tableColumn = column as? TableColumn
        if(tableColumn == null) {
            return column.columnName ==columnName
        } else {
            if(table == null)
                return false
            return (column.columnName ==columnName) && (tableColumn.table.getName() == table.getName())
        }
    }
}