package com.learn.notekeeper

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.learn.notekeeper.data.note.Note
import com.learn.notekeeper.data.note.Notes

/**
 * Created by mohammad on 11/5/2017.
 */
class NoteRecyclerAdapter : RecyclerView.Adapter<NoteRecyclerAdapter.ViewHolder> {
    companion object {
        val TAG = javaClass.simpleName
    }
    val context : Context
    val layoutInflater : LayoutInflater
    //val notes : List<Note>
    var cursor : Cursor

    constructor( context : Context, cursor : Cursor/*notes : List<Note>*/) {
        this.context = context
        layoutInflater = LayoutInflater.from(context)
        //this.notes = notes
        this.cursor = cursor
        populateColumnPositions()
    }
    private fun populateColumnPositions() {

    }
    private fun changeCursor(cursor : Cursor) {
        this.cursor.close()
        this.cursor = cursor

        populateColumnPositions()
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        cursor.moveToPosition(position)

        //val note = notes.get(position)
        val note = Note.create(cursor)

        if(holder == null) {
            Log.w(TAG, "onBindViewHolder viewHolder is null")
            return
        }
        holder.courseView.text = note.course?.courseTitle
        holder.titleView.text = note.noteTitle
        //holder.currentPosition = position
        holder.noteId = note.noteId

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.item_note_list, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
       //return notes.size
        return cursor.count
    }

    class ViewHolder : RecyclerView.ViewHolder {
        val titleView : TextView
        val courseView : TextView
        //var currentPosition : Int = -1
        var noteId : Int = Note.NEW_NOTE_ID
        constructor(itemView : View) : super(itemView) {
            titleView = itemView.findViewById(R.id.text_title)
            courseView = itemView.findViewById(R.id.text_course)

            itemView.setOnClickListener(itemViewClickListener)
        }

        val itemViewClickListener : View.OnClickListener =   View.OnClickListener { view ->
            val intent: Intent = Intent(view.context, NoteActivity::class.java)
            intent.putExtra(NoteListActivity.SELECTED_NOTE_ID, noteId)
            view.context.startActivity(intent)
        }

    }
}