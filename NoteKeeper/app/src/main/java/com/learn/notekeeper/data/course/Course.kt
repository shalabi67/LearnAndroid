package com.learn.notekeeper.data.course

/**
 * Created by mohammad on 11/3/2017.
 */
data class Course(val courseId:Int, var courseTitle:String) {
    override fun toString(): String {
        return courseTitle;
    }
}