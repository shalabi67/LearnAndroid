package com.learn.notekeeper

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import com.learn.notekeeper.data.course.Course
import com.learn.notekeeper.data.course.Courses
import com.learn.notekeeper.data.note.Note
import com.learn.notekeeper.data.note.Notes

import kotlinx.android.synthetic.main.activity_note.*

class NoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
        setSupportActionBar(toolbar)

        val spinner = findViewById<Spinner>(R.id.spinner_courses)
        val coursesAdapter = ArrayAdapter<Course>(this, android.R.layout.simple_spinner_item, Courses.courses)
        coursesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = coursesAdapter

        //readFromIntent(getNoteUsingExtra)
        readFromIntent(getNoteUsingNotePosition)

    }

    private fun readFromIntent(getNote : () -> Note?) {
        val note = getNote()
        if(note == null)
            return;

        val titleView = findViewById<TextView>(R.id.text_note_title)
        titleView.text = note.noteTitle

        val textView = findViewById<TextView>(R.id.text_note_text)
        textView.text = note.noteText

        val spinnerView = findViewById<Spinner>(R.id.spinner_courses)
        val index = Courses.courses.indexOfFirst { course -> course.courseTitle == note.course.courseTitle }
        spinnerView.setSelection(index)
    }
    val getNoteUsingExtra : () -> Note? = { intent.getParcelableExtra<Note>(NoteListActivity.NOTE)}
    val getNoteUsingNotePosition : () -> Note? = {
        val position = intent.getIntExtra(NoteListActivity.NOTE_POSITION, -1)
        if(position == -1)
             null
        else  {
            val note: Note = Notes.notes[position]
            note
        }

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_note, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
