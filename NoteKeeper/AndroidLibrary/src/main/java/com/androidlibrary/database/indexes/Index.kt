package com.androidlibrary.database.indexes

import com.androidlibrary.database.DatabaseOperations
import com.androidlibrary.database.Table
import com.androidlibrary.database.column.Column

/**
 * Created by mohammad on 11/24/2017.
 * CREATE INDEX index_name ON table_name(column1, column2, ...)
 */
class Index(val table : Table, val columns : List<String>, var indexName : String = "") {
    init {
        if(indexName.isEmpty()) {
            indexName = table.getName()
            for(columnName in columns) {
                indexName += "_$columnName"
            }
        }
    }
    fun create(databaseOperations: DatabaseOperations) {
        val sql = "CREATE INDEX $indexName ON ${table.getName()}(${columns.joinToString(",")})"
        databaseOperations.executeSQL(sql)
    }


}