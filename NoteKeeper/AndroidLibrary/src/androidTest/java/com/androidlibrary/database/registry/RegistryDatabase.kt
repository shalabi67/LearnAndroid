package com.androidlibrary.database.registry

import android.content.Context
import com.androidlibrary.database.Database

/**
 * Created by mohammad on 11/22/2017.
 */
class RegistryDatabase : Database {
    protected constructor(context: Context) :
            super(context, RegistryDatabase.databaseName, null,  RegistryDatabase.databaseVersion) {

    }

    companion object {
        val databaseName = "registry.db"
        val databaseVersion = 1
        fun create(context: Context) : RegistryDatabase {
            val registryDatabase = RegistryDatabase(context)
            registryDatabase.addTable(Registry())

            return registryDatabase
        }
    }
}