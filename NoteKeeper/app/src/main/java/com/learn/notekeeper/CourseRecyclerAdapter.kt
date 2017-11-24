package com.learn.notekeeper

import android.content.Context
import android.database.Cursor
import android.support.design.widget.Snackbar
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.learn.notekeeper.data.course.Course
import com.learn.notekeeper.data.course.Courses
import com.learn.notekeeper.datalayer.CoursesTable

/**
 * Created by mohammad on 11/5/2017.
 */
class CourseRecyclerAdapter : RecyclerView.Adapter<CourseRecyclerAdapter.ViewHolder> {
    companion object {
        val TAG = javaClass.simpleName
    }
    val context : Context
    val layoutInflater : LayoutInflater
    //val courses : List<Course>
    val cursor : Cursor

    constructor(context : Context, cursor : Cursor) {
        this.context = context
        layoutInflater = LayoutInflater.from(context)
        this.cursor = cursor
    }
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        cursor.moveToPosition(position)
        val course = CoursesTable().read<Course>(cursor)

        if(holder == null) {
            Log.w(TAG, "onBindViewHolder viewHolder is null")
            return
        }
        holder.courseView.text = course.courseTitle
        holder.currentPosition = position

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.item_course_list, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = cursor.count

    inner class ViewHolder : RecyclerView.ViewHolder {
        val courseView : TextView
        var currentPosition : Int = -1

        constructor(itemView : View) : super(itemView) {
            courseView = itemView.findViewById(R.id.text_course)

            itemView.setOnClickListener(itemViewClickListener)
        }

        val itemViewClickListener : View.OnClickListener =   View.OnClickListener { view ->
            /*
            val intent: Intent = Intent(view.context, NoteActivity::class.java)
            intent.putExtra(NoteListActivity.NOTE_POSITION, currentPosition)
            view.context.startActivity(intent)
            */
            cursor.moveToPosition(currentPosition)
            Snackbar.make(view, "The selected course: ${CoursesTable().read<Course>(cursor)}",
                    Snackbar.LENGTH_LONG).show()
        }

    }
}