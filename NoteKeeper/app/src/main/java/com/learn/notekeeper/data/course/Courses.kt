package com.learn.notekeeper.data.course

import android.database.Cursor
import com.androidlibrary.database.DatabaseOperations
import com.learn.notekeeper.datalayer.CoursesTable

/**
 * Created by mohammad on 11/3/2017.
 */
object Courses {
    /*
    val courses = listOf(
            Course(1, "Android"),
            Course(2, "Kotlin"),
            Course(3, "C++"),
            Course(4, "Java"))
*/
    var courses : MutableList<Course> = mutableListOf()
    fun getCourse(courseId:Int) : Course = courses.filter { course-> course.courseId == courseId }.first()

    fun getCourses(databaseOperations : DatabaseOperations) {
        val cursor = getCoursesCursor(databaseOperations)
        courses = CoursesTable().fill(cursor)
    }
    fun getCoursesCursor(databaseOperations : DatabaseOperations) : Cursor {
        val orderBy = "${CoursesTable.TITLE}"
        return databaseOperations.query(CoursesTable(), orderBy = orderBy)
    }
}