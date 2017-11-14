package com.learn.notekeeper.note_activity

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.DrawerActions
import android.support.test.espresso.contrib.NavigationViewActions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.learn.notekeeper.MainActivity
import com.learn.notekeeper.NoteRecyclerAdapter
import com.learn.notekeeper.R
import com.learn.notekeeper.data.note.Note
import com.learn.notekeeper.data.note.Notes
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by mshalabi on 08.11.17.
 */
@RunWith(AndroidJUnit4::class)
class MainActivityNotesTests {
    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule<MainActivity>(MainActivity::class.java)


    @Test
    fun mainActivityOpenDrawer() {
        val noteIndex : Int = 0

        //open the navigation drawer
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open())

        //click on notes option
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_notes))

        //click on the first node on the recycle view.
        onView(withId(R.id.list_items)).perform(RecyclerViewActions.actionOnItemAtPosition<NoteRecyclerAdapter.ViewHolder>(noteIndex, click()))

        //verify the expected values
        val notesNumber = Notes.notes.size-1
        for(i in 0 .. notesNumber) {
            //get node
            val note = Notes.notes[i]
            onView(withId(R.id.spinner_courses)).check(
                    ViewAssertions.matches(withSpinnerText(note.course?.courseTitle))
            )
            onView(withId(R.id.text_note_title)).check(
                    ViewAssertions.matches(withText(note.noteTitle))
            )
            onView(withId(R.id.text_note_text)).check(
                    ViewAssertions.matches(withText(note.noteText))
            )

            //click on next and check is is enabled
            if(i < notesNumber) {
                onView(allOf(withId(R.id.action_next_note), isEnabled())).perform(click())
            }
        }

        onView(withId(R.id.action_next_note)).check(matches(not(isEnabled())))
    }

}