package com.learn.notekeeper

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import com.learn.notekeeper.data.note.Note
import com.learn.notekeeper.data.note.Notes

import kotlinx.android.synthetic.main.activity_note_list.*

class NoteListActivity : AppCompatActivity() {
    companion object {
        val NOTE = "com.learn.notekeeper.NOTE"
        val NOTE_POSITION = "com.learn.notekeeper.NOTE_POSITION"
        val SELECTED_NOTE_ID = "com.learn.notekeeper.SELECTED_NOTE_ID"
    }

    lateinit var noteRecyclerAdapter : NoteRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            startActivity(Intent(this, NoteActivity::class.java))
            Snackbar.make(view, "Adding new note", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        initNotesList()
    }

    private fun initNotesList() {
        /*
        val notesList = findViewById<ListView>(R.id.list_notes)
        val notesAdapter = ArrayAdapter<Note>(this, android.R.layout.simple_list_item_1, Notes.notes)
        notesList.adapter = notesAdapter

        //notesList.setOnItemClickListener(listViewItemClick)

        notesList.setOnItemClickListener(listViewItemClickUsingNotePosition)
        */

        val notesRecyclerView = findViewById<RecyclerView>(R.id.list_notes)
        val notesLayoutManager = LinearLayoutManager(this)
        notesRecyclerView.layoutManager = notesLayoutManager

        noteRecyclerAdapter = NoteRecyclerAdapter(this, Notes.notes)
        notesRecyclerView.adapter = noteRecyclerAdapter

        //android.support.constraint.ConstraintLayout


    }

    val listViewItemClick : AdapterView.OnItemClickListener  =
            AdapterView.OnItemClickListener { adapterView, view, position, id ->
                val note:Note = adapterView.getItemAtPosition(position) as Note
                val intent:Intent = Intent(this, NoteActivity::class.java)
                intent.putExtra(NoteListActivity.NOTE, note)
                startActivity(intent)
            }

    val listViewItemClickUsingNotePosition : AdapterView.OnItemClickListener  =
            AdapterView.OnItemClickListener { adapterView, view, position, id ->
                val intent:Intent = Intent(this, NoteActivity::class.java)
                intent.putExtra(NoteListActivity.NOTE_POSITION, position)
                startActivity(intent)
            }

    override fun onResume() {
        super.onResume()

       // initNotesList()
        noteRecyclerAdapter.notifyDataSetChanged()
    }
}
