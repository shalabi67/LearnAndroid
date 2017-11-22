package com.learn.notekeeper.datalayer

import android.database.Cursor
import android.provider.BaseColumns
import com.androidlibrary.database.Data
import com.androidlibrary.database.Table
import com.androidlibrary.database.column.Column
import com.androidlibrary.database.column.ColumnProperty
import com.androidlibrary.database.column.ColumnPropertyBuilder
import com.androidlibrary.database.column.ColumnTypeEnum
import com.learn.notekeeper.data.course.Course
import com.learn.notekeeper.data.note.Note

/**
 * Created by mohammad on 11/22/2017.
 */
class NotesTable : Table {
    companion object {
        val tableName  = "notes"
        val TITLE = "notes_title"
        val DESCRIPTION = "description"
        val COURSE_ID = "course_id"
        private val tableColumns = mutableListOf<Column>()
        fun getColumns() : List<Column> {
            if(tableColumns.size != 0) {
                return tableColumns
            }
            tableColumns.add(Column(BaseColumns._ID, ColumnTypeEnum.Int,
                    ColumnPropertyBuilder()
                            .add(ColumnProperty.NOT_NULL)
                            .add(ColumnProperty.PRIMARY_KEY)
                            .build()))

            tableColumns.add(Column(TITLE, ColumnTypeEnum.Text,
                    ColumnPropertyBuilder()
                            .add(ColumnProperty.NOT_NULL)
                            .build()))

            tableColumns.add(Column(DESCRIPTION, ColumnTypeEnum.Text,
                    ColumnPropertyBuilder()
                            .add(ColumnProperty.NULL)
                            .build()))

            tableColumns.add(Column(COURSE_ID, ColumnTypeEnum.Int,
                    ColumnPropertyBuilder()
                            .add(ColumnProperty.NULL)
                            .build()))

            return tableColumns
        }
    }
    constructor()  {
        columns = getColumns()
        tableName = NotesTable.tableName
    }
    override fun <T : Data> read(cursor: Cursor): T {
        val note  = Note(cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2))

        return note as T
    }
}