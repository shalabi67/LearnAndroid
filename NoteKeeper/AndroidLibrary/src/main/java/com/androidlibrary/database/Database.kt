package com.androidlibrary.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase.CursorFactory
import android.database.sqlite.SQLiteDatabase
import com.androidlibrary.database.indexes.Index
import java.util.*
import kotlin.collections.HashMap


/**
 * Created by mohammad on 11/21/2017.
 */
abstract class Database {

    protected var dbHelper: SqliteHelper

    var databaseName = ""

    var databaseVersion = 1


    private var tables: MutableList<Table> = ArrayList<Table>()
    private var upgradeTables: MutableMap<Int, MutableList<Table>> = HashMap<Int, MutableList<Table>>()

    private var upgradeStatments: MutableMap<Int, MutableList<String>> = HashMap<Int, MutableList<String>>()

    private var indexes: MutableList<Index> = ArrayList<Index>()
    private var upgradeIndexes:  MutableMap<Int, MutableList<Index>> = HashMap<Int, MutableList<Index>>()

    protected constructor(context: Context, name: String, factory: CursorFactory?,
                          version: Int) {
        var fullPath = name

        /*
        if (Debugger.canLOG) {
            val file = context.getExternalFilesDir(null)
            if (file != null) {
                fullPath = "${file.absolutePath}/${name}"
            }
        }*/
        this.databaseName = fullPath
        this.databaseVersion = version

        @Suppress("LeakingThis")
        dbHelper = SqliteHelper(this, context, factory, version)

    }

    fun addTable(table: Table) {
        tables.add(table)
    }
    fun addUpgradeTable(version : Int, table: Table) {
        var tables : MutableList<Table> = ArrayList<Table>()
        if(upgradeTables.contains(version)) {
            tables = upgradeTables.getValue(version)
        }
        tables.add(table)
        upgradeTables.put(version, tables)
    }
    fun addIndex(index : Index) {
        indexes.add(index)
    }
    fun addUpgradeIndex(version : Int, index : Index) {
        var indexes : MutableList<Index> = ArrayList<Index>()
        if(upgradeIndexes.contains(version)) {
            indexes = upgradeIndexes.getValue(version)
        }
        indexes.add(index)
        upgradeIndexes.put(version, indexes)
    }

    fun create(database: SQLiteDatabase)  {
        val databaseOperations = DatabaseOperations(database)
        for (table in tables) {
            table.create(databaseOperations)
        }

        for(index in indexes) {
            index.create(databaseOperations)
        }

        upgrade(database, 1, databaseVersion)

        initDatabaseData(databaseOperations)
    }
    abstract fun initDatabaseData( databaseOperations : DatabaseOperations)



    fun upgrade(database: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val databaseOperations = DatabaseOperations(database)
        for(version in oldVersion+1 .. newVersion) {
            val tables = upgradeTables[version]
            if(tables != null) {
                for (table in tables) {
                    table.create(databaseOperations)
                }
            }

            val indexes = upgradeIndexes[version]
            if(indexes != null) {
                for (index in indexes) {
                    index.create(databaseOperations)
                }
            }

            val sqls = upgradeStatments[version]
            if(sqls != null) {
                for (sql in sqls) {
                    databaseOperations.executeSQL(sql)
                }
            }
        }

        initDatabaseUpgradeData(databaseOperations)
    }
    abstract fun initDatabaseUpgradeData( databaseOperations : DatabaseOperations)


    fun open() : DatabaseOperations = DatabaseOperations(dbHelper.writableDatabase)

    fun openReadable() : DatabaseOperations =  DatabaseOperations(dbHelper.readableDatabase)

}
