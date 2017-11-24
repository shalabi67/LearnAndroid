package com.learn.notekeeper.datalayer

import android.database.Cursor
import android.provider.BaseColumns._ID
import com.androidlibrary.database.Data
import com.androidlibrary.database.Table
import com.androidlibrary.database.column.*
import com.learn.notekeeper.data.course.Course

/**
 * Created by mohammad on 11/22/2017.
 */
class CoursesTable : Table {
    companion object {
        val tableName  = "courses"
        val TITLE = "course_title"
        private val tableColumns = Columns()
        fun getColumns() : Columns {
            if(tableColumns.size != 0) {
                return tableColumns
            }
            tableColumns.add(Column(_ID, ColumnTypeEnum.Int,
                    ColumnPropertyBuilder()
                            .add(ColumnProperty.NOT_NULL)
                            .add(ColumnProperty.PRIMARY_KEY)
                            .build()))

            tableColumns.add(Column(TITLE, ColumnTypeEnum.Text,
                    ColumnPropertyBuilder()
                            .add(ColumnProperty.NOT_NULL)
                            .build()))

            return tableColumns
        }
    }
    constructor()  {
        columns = getColumns()
        tableName = CoursesTable.tableName
    }
    override fun <T : Data> read(cursor: Cursor): T {
        val course  = Course(cursor.getInt(0),
                cursor.getString(1))

        return course as T
    }
}