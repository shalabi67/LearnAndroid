package com.learn.notekeeper.datalayer

import android.provider.ContactsContract
import com.androidlibrary.database.DatabaseOperations
import com.androidlibrary.database.Table
import com.learn.notekeeper.data.course.Course
import com.learn.notekeeper.data.note.Note

/**
 * Created by mohammad on 11/22/2017.
 */
class NoteKeeperInitialData {
    fun initCourses(databaseOperations : DatabaseOperations) {
        val courses = arrayOf(Course(1,"Android Programming with Intents")
                ,Course(2,"Android Async Programming and Services")
                ,Course(3,"Java Fundamentals: The Java Language")
                ,Course(4,"Java Fundamentals: The Core Platform"))

        val coursesTable = CoursesTable()

        for(course in courses) {
            databaseOperations.insert(coursesTable, course)
        }

        initNotes(databaseOperations,  courses)
    }

    private fun initNotes(databaseOperations : DatabaseOperations, courses :Array<Course>) {
        val notes = mutableListOf<Note>()
        notes.addAll(initNotesForCourse1(courses[0]))
        notes.addAll(initNotesForCourse2(courses[1]))
        notes.addAll(initNotesForCourse3(courses[2]))
        notes.addAll(initNotesForCourse4(courses[3]))

        val notesTable = NotesTable()
        for(note in notes) {
            databaseOperations.insert(notesTable, note)
        }

    }
    private fun initNotesForCourse1(course : Course) :List<Note> {
        return listOf<Note>( Note(1, "Dynamic intent resolution", "Wow, intents allow components to be resolved at runtime", course)
        , Note(2, "Delegating intents", "PendingIntents are powerful; they delegate much more than just a component invocation", course))
    }

    private fun initNotesForCourse2(course : Course) :List<Note> {
        return listOf<Note>( Note(3, "Service default threads", "Did you know that by default an Android Service will tie up the UI thread?", course)
                , Note(4, "Long running operations", "Foreground Services can be tied to a notification icon", course))
    }

    private fun initNotesForCourse3(course : Course) :List<Note> {
        return listOf<Note>( Note(5, "Parameters", "Leverage variable-length parameter lists?", course)
                , Note(6, "Anonymous classes", "Anonymous classes simplify implementing one-use types", course))
    }

    private fun initNotesForCourse4(course : Course) :List<Note> {
        return listOf<Note>( Note(7, "Compiler options", "The -jar option isn't compatible with with the -cp option", course)
                , Note(8, "Serialization", "Remember to include SerialVersionUID to assure version compatibility", course))
    }
}