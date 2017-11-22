package com.androidlibrary.database

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.androidlibrary.DatabaseTestingActivity
import com.androidlibrary.database.registry.Registry
import com.androidlibrary.database.registry.RegistryData
import com.androidlibrary.database.registry.RegistryDatabase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by mohammad on 11/22/2017.
 */
@RunWith(AndroidJUnit4::class)
class DatabaseCreationTest {
    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule<DatabaseTestingActivity>(DatabaseTestingActivity::class.java)

    @Test
    fun createDatabase() {
        val registryDatabase = RegistryDatabase.create(mActivityTestRule.activity.applicationContext)
        val databaseOperations = registryDatabase.open()
        val registryTable = Registry()
        val registryData = RegistryData()
        registryData.key = "key1"
        registryData.value = "value of key1"
        registryData.type = 1


        databaseOperations.insert(registryTable, registryData)


    }
}