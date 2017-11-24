package com.learn.notekeeper.data.note

import android.content.Context
import android.database.Cursor
import android.provider.BaseColumns
import android.util.Log
import com.androidlibrary.database.DatabaseOperations
import com.learn.notekeeper.data.course.Courses
import com.learn.notekeeper.datalayer.CoursesTable
import com.learn.notekeeper.datalayer.NotesKeeperDatabase
import com.learn.notekeeper.datalayer.NotesTable
import com.learn.notekeeper.datalayer.NotesView

/**
 * Created by mohammad on 11/3/2017.
 */
object Notes {
    val TAG = Notes::class.java.name
    private var id : Int =0;
    /*
    val notes = mutableListOf<Note>(
            Note(++id, "note 1", "", Courses.getCourse(4)),
            Note(++id, "note 2", "", Courses.getCourse(2)),
            Note(++id, "note 3", "", Courses.getCourse(4)),
            Note(++id, "note 4", "", Courses.getCourse(1)),
            Note(++id, "note 5", "", Courses.getCourse(3)))

            */
    var notes : MutableList<Note> = mutableListOf()

    fun addNote(note : Note) : Note {
        //val note = Note(++id, "", "")
        note.noteId = ++id
        notes.add(note)
        return note
    }

    fun createNote(noteTitle:String, noteText:String) : Note {
        return Note(++id, noteTitle, noteText)
    }

    fun getNotes(databaseOperations : DatabaseOperations) {
        val cursor = getNotesCursor(databaseOperations)
        notes = NotesView().fill<Note>(cursor)
    }

    fun getNotesCursor(databaseOperations: DatabaseOperations): Cursor {
        val notesView = NotesView()
        val courseColumn = notesView.columns.getColumnByName(CoursesTable.TITLE, CoursesTable())
        val titleColumn = notesView.columns.getColumnByName(NotesTable.TITLE, NotesTable())

        val orderBy = "${courseColumn.getColumnNameForQuery()}, ${titleColumn.getColumnNameForQuery()}"
        return databaseOperations.query(NotesView(), orderBy = orderBy)
    }

    fun getNoteById(databaseOperations : DatabaseOperations, noteId : Int) : Note {
        val cursor = getNotesByIdCursor(noteId, databaseOperations)
        val notes = NotesView().fill<Note>(cursor)
        if(notes.size > 1) {
            Log.e(TAG, "getNoteById found incorrect number of nodes for id: $noteId")
        } else if(notes.size == 0) {
            Log.e(TAG, "getNoteById found no node with id: $noteId")
            return Note.getEmptyNote()
        }
        return notes[0]
    }

    fun getNotesByIdCursor(noteId: Int, databaseOperations: DatabaseOperations): Cursor {
        val notesView = NotesView()
        val noteIdColumnName = notesView.columns.getColumnByName(BaseColumns._ID, NotesTable())
        val selectionCriteria = "${noteIdColumnName.getColumnNameForQuery()} = ?"
        val selectionParameters: Array<String> = arrayOf(noteId.toString())

        return databaseOperations.query(notesView, selectionCriteria, selectionParameters)
    }

    fun updateNote(note : Note, databaseOperations: DatabaseOperations) {
        databaseOperations.update(NotesTable(), note)

    }
}