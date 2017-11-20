package com.learn.notekeeper

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import com.learn.notekeeper.data.course.Courses
import com.learn.notekeeper.data.note.Notes
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    companion object {
        val NOTE = "com.learn.notekeeper.NOTE"
        val NOTE_POSITION = "com.learn.notekeeper.NOTE_POSITION"
    }

    lateinit var listRecyclerView : RecyclerView

    lateinit var noteRecyclerAdapter : NoteRecyclerAdapter
    lateinit var coursesRecyclerAdapter : CourseRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            startActivity(Intent(this, NoteActivity::class.java))
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        PreferenceManager.setDefaultValues(this, R.xml.pref_general, false)
        PreferenceManager.setDefaultValues(this, R.xml.pref_data_sync, false)
        PreferenceManager.setDefaultValues(this, R.xml.pref_notification, false)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        listRecyclerView = findViewById<RecyclerView>(R.id.list_items)

        initRecycleAdapters()

        displayNotes()
    }

    private fun displayNotes() {
        listRecyclerView.layoutManager = LinearLayoutManager(this)
        listRecyclerView.adapter = noteRecyclerAdapter

        //check notes item of the navaigation
        selectNavicationItem(R.id.nav_notes)
    }

    private fun displayCourses() {
        listRecyclerView.layoutManager = GridLayoutManager(this,
                resources.getInteger(R.integer.courses_columns_count))
        listRecyclerView.adapter = coursesRecyclerAdapter

        //check notes item of the navaigation
        selectNavicationItem(R.id.nav_courses)
    }

    private fun selectNavicationItem(itemId : Int) {
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.menu.findItem(itemId).setChecked(true)
    }

    override fun onResume() {
        super.onResume()

        // initNotesList()
        //noteRecyclerAdapter.notifyDataSetChanged()
        displayNotes()
        updateNavigationHeader()
    }

    private fun updateNavigationHeader() {
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        val headerView = navigationView.getHeaderView(0)
        val userNameView = headerView.findViewById<TextView>(R.id.text_user_name)
        val emailView = headerView.findViewById<TextView>(R.id.text_email_address)

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val emailAddress = sharedPreferences.getString("user_email_address", "")
        val fullName = sharedPreferences.getString("user_display_name", "")

        userNameView.text = fullName
        emailView.text = emailAddress
    }

    private fun initRecycleAdapters() {
        coursesRecyclerAdapter = CourseRecyclerAdapter(this, Courses.courses)
        noteRecyclerAdapter = NoteRecyclerAdapter(this, Notes.notes)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings ->  {
                startActivity(Intent(this, SettingsActivity::class.java))
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_notes -> {
                displayNotes()
                showMessage(R.string.nots_navigation_message)
            }
            R.id.nav_courses -> {
                displayCourses()
                showMessage(R.string.courses_navigation_message)

            }
            R.id.nav_share -> {
                val view = findViewById<RecyclerView>(R.id.list_items)
                val message = "Share to - " +
                        PreferenceManager.getDefaultSharedPreferences(this).getString(
                                "user_favorite_network", "")
                Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
    fun showMessage(messageId : Int) {
        val view = findViewById<RecyclerView>(R.id.list_items)
        Snackbar.make(view, messageId, Snackbar.LENGTH_LONG).show()
    }
}
