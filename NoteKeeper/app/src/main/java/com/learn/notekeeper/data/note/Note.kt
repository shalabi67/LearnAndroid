package com.learn.notekeeper.data.note

import android.content.ContentValues
import android.database.Cursor
import android.graphics.Bitmap
import android.os.Parcel
import android.os.Parcelable
import android.provider.BaseColumns
import com.androidlibrary.database.Data
import com.learn.notekeeper.data.course.Course
import com.learn.notekeeper.datalayer.CoursesTable
import com.learn.notekeeper.datalayer.NotesTable
import com.learn.notekeeper.datalayer.NotesView

/**
 * Created by mohammad on 11/3/2017.
 */
data class Note(var noteId : Long, var noteTitle:String, var noteText:String) : Parcelable, Data {

    companion object {
        val NEW_NOTE_ID : Long = 0
        fun getEmptyNote() : Note {
            return Note(NEW_NOTE_ID, "", "")
        }

        fun create(cursor : Cursor) : Note = NotesView().read<Note>(cursor)


        var CREATOR: Parcelable.Creator<Note> = object : Parcelable.Creator<Note> {
            override fun createFromParcel(parcel: Parcel): Note {
                return Note(parcel)
            }

            override fun newArray(size: Int): Array<Note?> {
                return arrayOfNulls<Note>(size)
            }
        }
    }
    var course : Course? = null
    var image : Bitmap? = null
    constructor( noteId : Long,  noteTitle:String,  noteText:String,  course : Course) : this(noteId, noteTitle, noteText) {
        this.course = course
    }
    constructor() : this(0, "", "") {

    }

    constructor(parcel: Parcel) : this(
            parcel.readLong(),
            parcel.readString(),
            parcel.readString(),
            parcel.readParcelable(Course::class.java.classLoader)) {
    }

    override fun writeToParcel(dest: Parcel?, flag: Int) {
        dest?.writeLong(noteId)
        dest?.writeString(noteTitle)
        dest?.writeString(noteText)

        dest?.writeParcelable(course, 0)
    }

    fun copyValues(note:Note) {
        noteId = note.noteId
        noteTitle = note.noteTitle
        noteText = note.noteText
        val course = note.course
        if(course != null) {
            this.course = Course(course.courseId, course.courseTitle)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "Course Title: ${course?.courseTitle} | Note Title: $noteTitle"
    }


    override fun getContentValues(): ContentValues {
        val contentValues = ContentValues()
        //contentValues.put(BaseColumns._ID, noteId)
        contentValues.put(NotesTable.TITLE, noteTitle)
        contentValues.put(NotesTable.DESCRIPTION, noteText)
        contentValues.put(NotesTable.COURSE_ID, course?.courseId)

        return contentValues
    }
    override fun getPrimaryKey(): String = noteId.toString()
}