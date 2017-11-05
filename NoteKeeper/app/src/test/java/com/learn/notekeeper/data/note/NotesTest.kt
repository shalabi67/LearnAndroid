package com.learn.notekeeper.data.note

import org.junit.Assert
import org.junit.Test

/**
 * Created by mohammad on 11/5/2017.
 */
class NotesTest {
    private val noteTitle = "node title"
    private val noteText = "node text"
    private val noteId = -1

    @Test
    fun addNewNote() {
        val beginSize = Notes.notes.size

        val note = Notes.addNote(Note(noteId, noteTitle, noteText))

        val newSize = Notes.notes.size


        Assert.assertEquals(beginSize + 1, newSize)
        Assert.assertEquals(note, Notes.getNote(note.noteId))
    }

    fun Notes.getNote(noteId : Int) : Note {
        val nodes =  Notes.notes.filter({note -> note.noteId == noteId})
        if(nodes.size > 1) {
            throw Exception("Expected single node")
        }

        return nodes[0]
    }

}