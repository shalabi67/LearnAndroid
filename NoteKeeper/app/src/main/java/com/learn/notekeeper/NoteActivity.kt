package com.learn.notekeeper

import android.app.Activity
import android.content.CursorLoader
import android.content.Intent
import android.content.Loader
import android.database.Cursor
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.BaseColumns
import android.provider.MediaStore
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import com.androidlibrary.database.DatabaseOperations
import com.androidlibrary.ui.loader.DataFeeder
import com.androidlibrary.ui.loader.DataLoader
import com.learn.notekeeper.data.course.Course
import com.learn.notekeeper.data.course.Courses
import com.learn.notekeeper.data.course.CoursesLoader
import com.learn.notekeeper.data.note.Note
import com.learn.notekeeper.data.note.NoteLoader
import com.learn.notekeeper.data.note.Notes
import com.learn.notekeeper.datalayer.CoursesTable
import com.learn.notekeeper.datalayer.NotesKeeperDatabase
import com.learn.notekeeper.datalayer.NotesTable
import com.learn.notekeeper.datalayer.NotesView

import kotlinx.android.synthetic.main.activity_note.*

class NoteActivity : AppCompatActivity(), DataFeeder {


    companion object {
        val CAMERA_GET_IMAGE = 1
        val ORIGINAL_NOTE = "com.learn.notekeeper.ORIGINAL_NOTE"
        val TAG = javaClass.simpleName
        val NEW_NOTE_POSITION = -1

    }
    lateinit private var spinner : Spinner
    lateinit private var titleView : TextView
    lateinit private var textView : TextView
    lateinit var imageView : ImageView

    lateinit private var note:Note
    lateinit private var oldNote:Note

    var position : Int = NEW_NOTE_POSITION
    var noteId : Int = Note.NEW_NOTE_ID
    lateinit var databaseOperations : DatabaseOperations

    private lateinit var coursesAdapter: SimpleCursorAdapter


    private var isSave = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
        setSupportActionBar(toolbar)

        imageView = findViewById<ImageView>(R.id.imageview_image)

        databaseOperations = NotesKeeperDatabase.create(this).openReadable()

        spinner = findViewById<Spinner>(R.id.spinner_courses)
        //val coursesAdapter = ArrayAdapter<Course>(this, android.R.layout.simple_spinner_item, Courses.courses)
        displayCourses(null)
        loaderManager.initLoader(CoursesLoader.ID, null, CoursesLoader(this, databaseOperations))

        noteId = intent.getIntExtra(NoteListActivity.SELECTED_NOTE_ID, Note.NEW_NOTE_ID)
        if(noteId != Note.NEW_NOTE_ID) {
            loaderManager.initLoader(NoteLoader.ID, null, NoteLoader(noteId, this, databaseOperations))
        }

        //displayNote(getNoteUsingExtra)
        //displayNote(getNoteUsingNotePosition)
        //displayNote(getNoteUsingNoteId)


        if(savedInstanceState != null) {
            Log.d(TAG, "OnCreate savedInstanceState not null")
            restoreOriginalNote(savedInstanceState)
        }



    }
    var noteDataLoaded = false
    var coursesDataLoaded = false
    override fun fillData(loaderId : Int, cursor : Cursor) {
        if(loaderId == NoteLoader.ID) {
            note = NotesView().read<Note>(cursor)
            noteDataLoaded = true

            saveOriginalNote()
        } else if(loaderId == CoursesLoader.ID) {
            coursesAdapter.changeCursor(cursor)
            coursesDataLoaded = true

        }
        if(noteDataLoaded && coursesDataLoaded) {
            displayNote()
        }
    }

    private fun displayNote() {
        titleView = findViewById<TextView>(R.id.text_note_title)
        titleView.text = note.noteTitle

        textView = findViewById<TextView>(R.id.text_note_text)
        textView.text = note.noteText

        imageView.setImageBitmap(note.image)

        val spinnerView = findViewById<Spinner>(R.id.spinner_courses)
        //val index = Courses.courses.indexOfFirst { course -> course.courseTitle == note.course?.courseTitle }
        val index = getCourseIndex(note.course?.courseTitle)
        if(index < 0) {
            Log.e(TAG, "displayNote could not find course")
            return
        }
        spinnerView.setSelection(index)
    }

    private fun displayCourses(cursor : Cursor?) {
         coursesAdapter = SimpleCursorAdapter(this,
                android.R.layout.simple_spinner_item,
                 cursor,
                arrayOf(CoursesTable.TITLE),
                intArrayOf(android.R.id.text1),
                0)
        coursesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = coursesAdapter
    }

    override fun onDestroy() {
        super.onDestroy()

        coursesAdapter.changeCursor(null)
        databaseOperations.close()
    }

    private fun restoreOriginalNote(savedInstanceState: Bundle?) {
        Log.d(TAG, "restoreOriginalNote")
        val originalNote = savedInstanceState?.getParcelable<Note>(ORIGINAL_NOTE)
        if(originalNote != null) {
            oldNote = originalNote
        }
    }

    private fun saveOriginalNote() {
        Log.d(TAG, "saveOriginalNote")
        this.oldNote = this.note.copy()
        val course = note.course
        if (course != null) {
            oldNote.course = Course(course.courseId, course.courseTitle)
        }
    }

    private fun displayNote(getNote : () -> Note?) {
        val note = getNote()
        if(note == null) {
            Log.w(TAG, "displayNote note is null")
            return;
        }
        this.note = note

        displayNote()
    }

    private fun getCourseIndex(courseTitle: String?): Int {
        if(courseTitle == null) {
            Log.e(TAG, "getCourseIndex note has no course attached to it.")
            return -1
        }

        val cursor = coursesAdapter.cursor
        if(cursor == null)
            return -1
        var flag = cursor.moveToFirst()
        var position = 0
        val titleColumnIndex = cursor.getColumnIndex(CoursesTable.TITLE)
        while(flag) {
            val title = cursor.getString(titleColumnIndex)
            if(title == courseTitle)
                return position

            position++
            flag = cursor.moveToNext()
        }

        Log.e(TAG, "getCourseIndex could not find title: $courseTitle")
        return -1
    }

    private val getNoteUsingExtra : () -> Note? = { intent.getParcelableExtra<Note>(NoteListActivity.NOTE)}
    /*private val getNoteUsingNotePosition : () -> Note? = {
        position = intent.getIntExtra(NoteListActivity.NOTE_POSITION, -1)
        if(position == NEW_NOTE_POSITION) {
            val note: Note = Note(NEW_NOTE_POSITION, "", "")
            note
        }
        else  {
            val note: Note = Notes.notes[position]
            note
        }

    }*/
    private val getNoteUsingNoteId : () -> Note? = {

        if(noteId == Note.NEW_NOTE_ID) {
            val note: Note = Note(Note.NEW_NOTE_ID, "", "")
            note
        }
        else  {
            val note: Note = Notes.getNoteById(databaseOperations, noteId)
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
            R.id.action_next_note -> moveToNextNode()
            R.id.action_cancel -> {
                isSave = false
                //note.copyValues(oldNote)

                finish()
                true
            }
            R.id.action_camera_image -> getImageFromCamera()
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun moveToNextNode(): Boolean {
        //save exiting node
        saveNote()

        //get next node
        ++position
        note = Notes.notes.get(position)

        //move to next node
        saveOriginalNote()
        displayNote({note})


        //disable cancel menue if needed
        invalidateOptionsMenu()
        return true
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
    override fun onPause() {
        super.onPause()

        if(!isSave) {
            return
        }
        saveNote()
    }

    private fun saveNote() {
        val databaseOperations = NotesKeeperDatabase.create(this).open()
        note.noteTitle = titleView.text.toString()
        note.noteText = textView.text.toString()
        note.course = getSelectedCourse()

        if (note.noteId == -1) {
            Notes.addNote(note)
        }
        else {
            Notes.updateNote(note, databaseOperations)
        }

        databaseOperations.close()
    }

    private fun getSelectedCourse(): Course {
        val selectedPosition = spinner.selectedItemPosition
        val cursor = coursesAdapter.cursor
        cursor.moveToPosition(selectedPosition)
        return CoursesTable().read<Course>(cursor)
    }

    override fun onResume() {
        super.onResume()

        isSave = true
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.putParcelable(ORIGINAL_NOTE, oldNote)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val cancelItem = menu?.findItem(R.id.action_next_note)
        cancelItem?.setEnabled(position < Notes.notes.size - 1)
        return super.onPrepareOptionsMenu(menu)
    }
}
