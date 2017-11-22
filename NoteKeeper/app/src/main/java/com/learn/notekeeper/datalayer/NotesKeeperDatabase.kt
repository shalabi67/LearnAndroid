package com.learn.notekeeper.datalayer

import android.content.Context
import com.androidlibrary.database.Database
import com.androidlibrary.database.DatabaseOperations

/**
 * Created by mohammad on 11/22/2017.
 */
class NotesKeeperDatabase : Database {
    companion object {
        val databaseName = "note-keeper.db"
        val databaseVersion = 1
        fun create(context: Context) : NotesKeeperDatabase {
            val registryDatabase = NotesKeeperDatabase(context)
            registryDatabase.addTable(CoursesTable())
            registryDatabase.addTable(NotesTable())

            return registryDatabase
        }
    }

    protected constructor(context: Context) :
            super(context, NotesKeeperDatabase.databaseName, null,  NotesKeeperDatabase.databaseVersion) {
    }

    override fun initDatabaseData(databaseOperations : DatabaseOperations) {
        NoteKeeperInitialData().initCourses(databaseOperations)
    }

    override fun initDatabaseUpgradeData(databaseOperations: DatabaseOperations) {
    }
}