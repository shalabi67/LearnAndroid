package com.androidlibrary.database.column

import com.androidlibrary.database.Table

/**
 * Created by mohammad on 11/22/2017.
 */
class TableColumn(val table : Table, column : Column) : Column(column.columnName, column.type, column.properties ) {

}