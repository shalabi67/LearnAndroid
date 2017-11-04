package com.learn.notekeeper.data.note

import com.learn.notekeeper.data.course.Courses

/**
 * Created by mohammad on 11/3/2017.
 */
object Notes {
    val notes = mutableListOf<Note>(Note(1, "note 1", "", Courses.getCourse(4)),
            Note(1, "note 1", "", Courses.getCourse(4)),
            Note(1, "note 2", "", Courses.getCourse(2)),
            Note(1, "note 3", "", Courses.getCourse(4)),
            Note(1, "note 4", "", Courses.getCourse(1)),
            Note(1, "note 5", "", Courses.getCourse(3)))
}