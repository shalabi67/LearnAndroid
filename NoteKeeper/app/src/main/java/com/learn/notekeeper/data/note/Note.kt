package com.learn.notekeeper.data.note

import android.os.Parcel
import android.os.Parcelable
import com.learn.notekeeper.data.course.Course

/**
 * Created by mohammad on 11/3/2017.
 */
data class Note(val noteId : Int, var noteTitle:String, var noteText:String, var course : Course) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readParcelable(Course::class.java.classLoader)) {
    }

    override fun writeToParcel(dest: Parcel?, flag: Int) {
        dest?.writeInt(noteId)
        dest?.writeString(noteTitle)
        dest?.writeString(noteText)

        dest?.writeParcelable(course, 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "Course Title: ${course.courseTitle} | Note Title: $noteTitle"
    }

    companion object CREATOR : Parcelable.Creator<Note> {
        override fun createFromParcel(parcel: Parcel): Note {
            return Note(parcel)
        }

        override fun newArray(size: Int): Array<Note?> {
            return arrayOfNulls(size)
        }
    }
}