package com.learn.notekeeper.datalayer

import android.database.Cursor
import android.provider.BaseColumns
import com.androidlibrary.database.Data
import com.androidlibrary.database.InnerJoin
import com.androidlibrary.database.Table
import com.androidlibrary.database.View
import com.androidlibrary.database.column.Column
import com.androidlibrary.database.column.ColumnProperty
import com.androidlibrary.database.column.ColumnPropertyBuilder
import com.androidlibrary.database.column.ColumnTypeEnum
import com.learn.notekeeper.data.course.Course
import com.learn.notekeeper.data.note.Note

/**
 * Created by mohammad on 11/22/2017.
 *
 * get notes and courses joined
 */
class NotesView : View(NotesTable()) {
    companion object {
        val viewName  = "notesView"
        private val viewColumns = mutableListOf<Column>()
        fun getColumns() : List<Column> {
            if(viewColumns.size != 0) {
                return viewColumns
            }
            View.addColumns(viewColumns, NotesTable(), NotesTable.getColumns())
            View.addColumns(viewColumns, CoursesTable(), CoursesTable.getColumns())

            return viewColumns
        }
    }

    init {
        columns = getColumns()
        tableName = viewName

        this.joinStatements.add(InnerJoin.buildInnerJoin(mainTable, CoursesTable(), NotesTable.COURSE_ID, BaseColumns._ID))
    }
    override fun <T : Data> read(cursor: Cursor): T {
        val note = NotesTable().read<Note>(cursor)
        note.course  = Course(cursor.getInt(4),
                cursor.getString(5))

        return note as T
    }
}