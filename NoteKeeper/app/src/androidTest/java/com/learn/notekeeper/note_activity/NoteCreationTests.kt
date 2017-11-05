package com.learn.notekeeper.note_activity

import android.support.test.espresso.Espresso
import android.support.test.espresso.ViewAction
import android.support.test.espresso.ViewAssertion
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.learn.notekeeper.NoteListActivity
import com.learn.notekeeper.R
import com.learn.notekeeper.data.course.Course
import com.learn.notekeeper.data.course.Courses
import com.learn.notekeeper.data.note.Notes
import kotlinx.android.synthetic.main.activity_note_list.view.*
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by mohammad on 11/5/2017.
 */
@RunWith(AndroidJUnit4::class)
class NoteCreationTests {
    @Rule
    @JvmField
    val noteListActivityRule : ActivityTestRule<NoteListActivity> = ActivityTestRule<NoteListActivity>(NoteListActivity::class.java)

    @Test
    fun createNewNote() {
        //val newNoteButton : ViewInteraction = Espresso.onView(ViewMatchers.withId(R.id.fab))
        //newNoteButton.perform(ViewActions.click())

        //click on the new note button
        Espresso.onView(ViewMatchers.withId(R.id.fab)).perform(ViewActions.click())


        //create note
        val note = Notes.createNote("testing title", "testing text")


        //set new note title and text
        Espresso.onView(ViewMatchers.withId(R.id.text_note_title))
                .perform(ViewActions.typeText(note.noteTitle))
                .check(ViewAssertions.matches(
                        ViewMatchers.withText(Matchers.containsString(note.noteTitle))))

        Espresso.onView(ViewMatchers.withId(R.id.text_note_text))
                .perform(ViewActions.typeText(note.noteText), ViewActions.closeSoftKeyboard())

        //Espresso.onView(ViewMatchers.withId(R.id.text_note_title)).perform(ViewActions.closeSoftKeyboard())

        Espresso.onView(ViewMatchers.withId(R.id.text_note_text))
                .check(ViewAssertions.matches(
                        ViewMatchers.withText(Matchers.containsString(note.noteText))))

        //select course
        val course = Courses.getCourse(3)
        Espresso.onView(ViewMatchers.withId(R.id.spinner_courses)).perform(ViewActions.click())
        Espresso.onData(Matchers.allOf(Matchers.instanceOf(Course::class.java),
                Matchers.equalTo(course)))
                .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.spinner_courses))
                .check(ViewAssertions.matches(
                ViewMatchers.withSpinnerText(Matchers.containsString(course.courseTitle))))

        Espresso.pressBack()
    }
}