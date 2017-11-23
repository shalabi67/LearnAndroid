package com.learn.notekeeper.data.note

import android.provider.BaseColumns
import android.util.Log
import com.androidlibrary.database.DatabaseOperations
import com.learn.notekeeper.data.course.Courses
import com.learn.notekeeper.datalayer.CoursesTable
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
        val notesView = NotesView()
        val courseColumn = notesView.getColumnByName(CoursesTable.TITLE, CoursesTable())
        val titleColumn = notesView.getColumnByName(NotesTable.TITLE, NotesTable())

        val orderBy = "${courseColumn.getColumnNameForQuery()}, ${titleColumn.getColumnNameForQuery()}"
        val cursor = databaseOperations.query(NotesView(), orderBy = orderBy)
        notes = NotesView().fill<Note>(cursor)
    }

    fun getNoteById(databaseOperations : DatabaseOperations, noteId : Int) : Note {
        val notesView = NotesView()
        val noteIdColumnName = notesView.getColumnByName(BaseColumns._ID, NotesTable())
        val selectionCriteria = "${noteIdColumnName.getColumnNameForQuery()} = ?"
        val selectionParameters : Array<String> = arrayOf(noteId.toString())

        val cursor = databaseOperations.query(notesView, selectionCriteria, selectionParameters)
        val notes = notesView.fill<Note>(cursor)
        if(notes.size > 1) {
            Log.e(TAG, "getNoteById found incorrect number of nodes for id: $noteId")
        } else if(notes.size == 0) {
            Log.e(TAG, "getNoteById found no node with id: $noteId")
            return Note.getEmptyNote()
        }
        return notes[0]
    }
}