package com.androidlibrary.database.registry

import android.content.Context
import com.androidlibrary.database.Database
import com.androidlibrary.database.DatabaseOperations

/**
 * Created by mohammad on 11/22/2017.
 */
class RegistryDatabase : Database {

    companion object {
        val databaseName = "registry.db"
        val databaseVersion = 1
        fun create(context: Context) : RegistryDatabase {
            val registryDatabase = RegistryDatabase(context)
            registryDatabase.addTable(Registry())

            return registryDatabase
        }
    }

    protected constructor(context: Context) :
            super(context, RegistryDatabase.databaseName, null,  RegistryDatabase.databaseVersion) {

    }

    override fun initDatabaseData(databaseOperations: DatabaseOperations) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initDatabaseUpgradeData(databaseOperations: DatabaseOperations) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}