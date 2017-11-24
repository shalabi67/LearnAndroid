package com.androidlibrary.database.registry

import android.database.Cursor
import com.androidlibrary.database.*
import com.androidlibrary.database.column.*

/**
 * Created by mohammad on 11/22/2017.
 */
class Registry : Table {
    companion object {
        val tableName  = "registry"
        val KEY = "key"
        val VALUE = "value"
        val TYPE = "type"
        private val tableColumns = Columns()
        fun getColumns() : Columns {
            if(tableColumns.size != 0) {
                return tableColumns
            }
            tableColumns.add(Column(KEY, ColumnTypeEnum.Text,
                    ColumnPropertyBuilder()
                            .add(ColumnProperty.NOT_NULL)
                            .add(ColumnProperty.UNIQUE)
                            .build()))

            tableColumns.add(Column(VALUE, ColumnTypeEnum.Text,
                    ColumnPropertyBuilder()
                            .add(ColumnProperty.NOT_NULL)
                            .build()))

            tableColumns.add(Column(TYPE, ColumnTypeEnum.Int,
                    ColumnPropertyBuilder()
                            .add(ColumnProperty.NOT_NULL)
                            .build()))
            return tableColumns
        }
    }
    constructor()  {
        columns = getColumns()
        tableName = Registry.tableName
    }

    override fun <T : Data> read(cursor: Cursor): T {
        val registry  = RegistryData()
        registry.key = cursor.getString(0)
        registry.value = cursor.getString(1)
        registry.type = cursor.getInt(2)

        return registry as T
    }
}