package com.learn.notekeeper.data.course

/**
 * Created by mohammad on 11/3/2017.
 */
object Courses {
    var courses = listOf(
            Course(1, "Android"),
            Course(2, "Kotlin"),
            Course(3, "C++"),
            Course(4, "Java"))

    fun getCourse(courseId:Int) : Course {
        return courses.filter { course-> course.courseId == courseId }.first()
    }
}