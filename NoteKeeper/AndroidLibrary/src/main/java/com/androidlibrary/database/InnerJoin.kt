package com.androidlibrary.database

/**
 * Created by mohammad on 11/22/2017.
 */
class InnerJoin {
    companion object {
        fun buildInnerJoin(table1 : Table, table2: Table,
                           table1JoinColumn : String, table2JoinColumn : String) : String {
            val table1Name = table1.getName()
            val table2Name = table2.getName()
            return "INNER JOIN $table2Name ON $table2Name.$table2JoinColumn = $table1Name.$table1JoinColumn "
        }
    }
    /*
    FROM
     tracks
    INNER JOIN albums ON Albums.AlbumId = tracks.AlbumId
tracks  <----  table1
albums <--      table 2
     */

}