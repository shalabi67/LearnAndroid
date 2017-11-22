package com.androidlibrary.database

import com.androidlibrary.database.column.Column
import com.androidlibrary.database.column.TableColumn

/**
 * Created by mohammad on 11/22/2017.
 */
abstract class View(val mainTable : Table) : Table() {
    companion object {
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
    fun getColumnName(col : Column) : String {
        val column = col as TableColumn
        return "${column.table.getName()}.${column.columnName} AS ${column.table.getName()}_${column.columnName}"
    }
    override fun getColumnNameForQuery(col : Column) : String {
        val column = col as TableColumn
        return "${column.table.getName()}_${column.columnName}"
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