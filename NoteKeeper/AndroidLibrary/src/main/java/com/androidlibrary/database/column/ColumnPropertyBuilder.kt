package com.androidlibrary.database.column

/**
 * Created by mohammad on 11/22/2017.
 */
class ColumnPropertyBuilder {
    private val columnProperties = mutableListOf<ColumnProperty>()

    fun add(columnProperty : ColumnProperty) : ColumnPropertyBuilder {
        columnProperties.add(columnProperty)
        return this
    }

    fun build() : Array<ColumnProperty> {
        return columnProperties.toTypedArray()
    }
}