package com.androidlibrary.database

import android.util.Log
import com.androidlibrary.database.column.Column
import com.androidlibrary.database.column.ColumnTypeEnum
import com.androidlibrary.database.column.TableColumn

/**
 * Created by mohammad on 11/22/2017.
 */
abstract class View(val mainTable : Table) : Table() {
    companion object {
        val TAG = View::class.java.name
        fun addColumns(list : MutableList<Column>, table : Table, columns : List<Column>) {
            for(column in columns) {
                list.add(TableColumn(table, column))
            }
        }
    }
    val joinStatements = mutableListOf<String>()
    override fun getCreateSql(): String {
        val function: (column:Column) -> String = {c -> getColumnName(c)}
        return "CREATE VIEW $tableName AS SELECT ${getColumnsString(function)} FROM ${mainTable.getName()} ${getJoinStatementsString()} "
    }
    private fun getColumnName(col : Column) : String {
        val column = col as TableColumn
        return "${column.table.getName()}.${column.columnName} AS ${column.table.getName()}_${column.columnName}"
    }

    /*override fun getColumnNameForQuery(col : Column) : String {
        val column = col as TableColumn
        return "${column.table.getName()}_${column.columnName}"
    }*/

    override fun getColumnByName(columnName : String, table : Table?) : Column {
        if(table == null) {
            return super.getColumnByName(columnName, table)
        }

        val columnList = columns.filter({ col -> isRequestedColumn(col, columnName, table)})
        if(columnList.isEmpty()) {
            Log.e(TAG, "getColumnByName return empty list for $columnName and ${table?.getName()}")
            return TableColumn(table, Column.getInvalidColumn())
        }

        return columnList[0]
    }
    private fun isRequestedColumn(column : Column, columnName : String, table : Table) : Boolean {
        val tableColumn = column as TableColumn
        if(tableColumn == null) {
            return column.columnName ==columnName
        } else {
            return (column.columnName ==columnName) && (tableColumn.table.getName() == table.getName())
        }
    }

    private fun getJoinStatementsString() : String = joinStatements.joinToString(" ")



    /*
    CREATE VIEW v_tracks
AS
SELECT
 trackid,
 tracks.name,
 albums.Title AS album,
 media_types.Name AS media,
 genres.Name AS genres
FROM
 tracks
INNER JOIN albums ON Albums.AlbumId = tracks.AlbumId
INNER JOIN media_types ON media_types.MediaTypeId = tracks.MediaTypeId
INNER JOIN genres ON genres.GenreId = tracks.GenreId;
     */

}