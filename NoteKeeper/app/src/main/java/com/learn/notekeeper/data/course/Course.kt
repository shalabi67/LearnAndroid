package com.learn.notekeeper.data.course

import android.content.ContentValues
import android.os.Parcel
import android.os.Parcelable
import android.provider.BaseColumns._ID
import com.androidlibrary.database.Data
import com.learn.notekeeper.datalayer.CoursesTable

/**
 * Created by mohammad on 11/3/2017.
 */
data class Course(val courseId:Int, var courseTitle:String) : Parcelable, Data {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString()) {
    }

    override fun describeContents(): Int {
        return 0;
    }

    override fun writeToParcel(dest: Parcel?, flag: Int) {
        dest?.writeInt(courseId)
        dest?.writeString(courseTitle)
    }

    override fun toString(): String {
        return courseTitle;
    }

    companion object CREATOR : Parcelable.Creator<Course> {
        override fun createFromParcel(parcel: Parcel): Course {
            return Course(parcel)
        }

        override fun newArray(size: Int): Array<Course?> {
            return arrayOfNulls(size)
        }
    }

    override fun getContentValues(): ContentValues {
        val contentValues = ContentValues()
        contentValues.put(_ID, courseId)
        contentValues.put(CoursesTable.TITLE, courseTitle)

        return contentValues
    }
    override fun getPrimaryKey(): String = courseId.toString()
}