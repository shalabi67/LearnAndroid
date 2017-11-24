package com.androidlibrary.database.column

import com.androidlibrary.database.column.ColumnProperty
import com.androidlibrary.database.column.ColumnPropertyEnum

/**
 * Created by mohammad on 11/21/2017.
 */
class DefaultColumnProperty(val propertyEnum: ColumnPropertyEnum) : ColumnProperty(propertyEnum)  {
    override fun toString(): String  = "default " + super.toString()
}