package com.learn.notekeeper

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import com.learn.notekeeper.data.course.Course
import com.learn.notekeeper.data.course.Courses
import com.learn.notekeeper.data.note.Note
import com.learn.notekeeper.data.note.Notes

import kotlinx.android.synthetic.main.activity_note.*
import java.net.URL

class NoteActivity : AppCompatActivity() {
    companion object {
        val CAMERA_GET_IMAGE = 1
    }
    lateinit private var spinner : Spinner
    lateinit private var titleView : TextView
    lateinit private var textView : TextView
    lateinit var imageView : ImageView

    lateinit private var note:Note
    lateinit private var oldNote:Note


    var isSave = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
        setSupportActionBar(toolbar)

        imageView = findViewById<ImageView>(R.id.imageview_image)

        spinner = findViewById<Spinner>(R.id.spinner_courses)
        val coursesAdapter = ArrayAdapter<Course>(this, android.R.layout.simple_spinner_item, Courses.courses)
        coursesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = coursesAdapter

        //readFromIntent(getNoteUsingExtra)
        readFromIntent(getNoteUsingNotePosition)

        this.oldNote = this.note.copy()
        val course = note.course
        if(course != null) {
            oldNote.course = Course(course.courseId, course.courseTitle)
        }


    }

    private fun readFromIntent(getNote : () -> Note?) {
        val note = getNote()
        if(note == null)
            return;
        this.note = note

        titleView = findViewById<TextView>(R.id.text_note_title)
        titleView.text = note.noteTitle

        textView = findViewById<TextView>(R.id.text_note_text)
        textView.text = note.noteText

        imageView.setImageBitmap(note?.image)

        val spinnerView = findViewById<Spinner>(R.id.spinner_courses)
        val index = Courses.courses.indexOfFirst { course -> course.courseTitle == note.course?.courseTitle }
        spinnerView.setSelection(index)
    }
    private val getNoteUsingExtra : () -> Note? = { intent.getParcelableExtra<Note>(NoteListActivity.NOTE)}
    private val getNoteUsingNotePosition : () -> Note? = {
        val position = intent.getIntExtra(NoteListActivity.NOTE_POSITION, -1)
        if(position == -1) {
            val note: Note = Note(-1, "", "")
            note
        }
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
            R.id.action_email_note -> sendEmail()
            R.id.action_cancel -> {
                isSave = false
                note.copyValues(oldNote)

                finish()
                true
            }
            R.id.action_camera_image -> getImageFromCamera()
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getImageFromCamera(): Boolean {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if(intent.resolveActivity(packageManager) == null) {
            return false
        }
        //intent.putExtra(MediaStore.EXTRA_OUTPUT, URI("a.dat"))
        startActivityForResult(intent, CAMERA_GET_IMAGE)

        return true
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode == Activity.RESULT_OK && requestCode == CAMERA_GET_IMAGE && data!=null) {
            val bitmap:Bitmap = data.extras.get("data") as Bitmap
            imageView.setImageBitmap(bitmap)
            note.image = bitmap
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun sendEmail() : Boolean {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "message/rfc2822"
        intent.putExtra(Intent.EXTRA_SUBJECT, titleView.text.toString())
        intent.putExtra(Intent.EXTRA_TEXT, "Shalabi Note: ${textView.text.toString()}")

        try {
            startActivity(intent)
        } catch(e : Exception) {
            Snackbar.make(titleView, "Could not send email. ${e.message}", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        return true
    }

    override fun onPause() {
        super.onPause()

        if(!isSave) {
            return
        }
        note.noteTitle = titleView.text.toString()
        note.noteText = textView.text.toString()
        note.course = spinner.selectedItem as Course

        if(note.noteId == -1) {
            Notes.addNewNote(note)
        }
    }

    override fun onResume() {
        super.onResume()

        isSave = true
    }
}
