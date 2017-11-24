package com.learn.notekeeper.datalayer

import android.content.Context
import com.androidlibrary.database.Database
import com.androidlibrary.database.DatabaseOperations
import com.androidlibrary.database.indexes.Index

/**
 * Created by mohammad on 11/22/2017.
 */
class NotesKeeperDatabase : Database {
    companion object {
        val databaseName = "note-keeper.db"
        val databaseVersion = 2
        fun create(context: Context) : NotesKeeperDatabase {
            val notesKeeperDatabase = NotesKeeperDatabase(context)
            val coursesTable = CoursesTable()
            val notesTable = NotesTable()
            notesKeeperDatabase.addTable(coursesTable)
            notesKeeperDatabase.addTable(notesTable)
            notesKeeperDatabase.addTable(NotesView())


            notesKeeperDatabase.addUpgradeIndex(2, Index(coursesTable, listOf(CoursesTable.TITLE)))
            notesKeeperDatabase.addUpgradeIndex(2, Index(notesTable, listOf(NotesTable.TITLE)))
            return notesKeeperDatabase
        }
    }

    private constructor(context: Context) :
            super(context, NotesKeeperDatabase.databaseName, null,  NotesKeeperDatabase.databaseVersion) {
    }

    override fun initDatabaseData(databaseOperations : DatabaseOperations) {
        NoteKeeperInitialData().initCourses(databaseOperations)
    }

    override fun initDatabaseUpgradeData(databaseOperations: DatabaseOperations) {
    }
}