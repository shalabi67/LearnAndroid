package com.androidlibrary.database.column

/**
 * Created by mohammad on 11/21/2017.
 */
open class ColumnProperty {
    var columnPropertyEnum: ColumnPropertyEnum
    constructor(propertyEnum: ColumnPropertyEnum) {
        this.columnPropertyEnum = propertyEnum
    }
    override fun toString(): String {
        var property = ""
        when (columnPropertyEnum) {
            ColumnPropertyEnum.Null -> property = "NULL"
            ColumnPropertyEnum.NotNull -> property = "NOT NULL"
            ColumnPropertyEnum.PrimaryKey -> property = "PRIMARY KEY"
            ColumnPropertyEnum.Autoincrement -> property = "AUTOINCREMENT"
            ColumnPropertyEnum.Unique -> property = "UNIQUE"
            else -> {
            }
        }
        return property
    }

    companion object {
        val NULL = ColumnProperty(ColumnPropertyEnum.Null)
        val NOT_NULL = ColumnProperty(ColumnPropertyEnum.NotNull)
        val PRIMARY_KEY = ColumnProperty(ColumnPropertyEnum.PrimaryKey)
        val AUTOINCREMENT = ColumnProperty(ColumnPropertyEnum.Autoincrement)
        val UNIQUE = ColumnProperty(ColumnPropertyEnum.Unique)
    }
}