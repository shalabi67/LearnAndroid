package com.learn.notekeeper.data.note

import com.learn.notekeeper.data.course.Courses

/**
 * Created by mohammad on 11/3/2017.
 */
object Notes {
    private var id : Int =0;
    val notes = mutableListOf<Note>(
            Note(++id, "note 1", "", Courses.getCourse(4)),
            Note(++id, "note 2", "", Courses.getCourse(2)),
            Note(++id, "note 3", "", Courses.getCourse(4)),
            Note(++id, "note 4", "", Courses.getCourse(1)),
            Note(++id, "note 5", "", Courses.getCourse(3)))

    fun addNote(note : Note) : Note {
        //val note = Note(++id, "", "")
        note.noteId = ++id
        notes.add(note)
        return note
    }

    fun createNote(noteTitle:String, noteText:String) : Note {
        return Note(++id, noteTitle, noteText)
    }
}