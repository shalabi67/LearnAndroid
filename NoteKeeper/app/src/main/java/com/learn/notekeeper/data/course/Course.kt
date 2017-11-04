package com.learn.notekeeper.data.course

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by mohammad on 11/3/2017.
 */
data class Course(val courseId:Int, var courseTitle:String) : Parcelable {
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
}