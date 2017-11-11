package com.learn.services

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.ResultReceiver
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import com.learn.services.intent_service.MyIntentService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var intentServiceTextView : TextView

    val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        val startedServiceTextView = findViewById<TextView>(R.id.main_textview_started)
        intentServiceTextView = findViewById<TextView>(R.id.main_textview_intentservice)
    }
    fun startTheStartedService(view : View) {
        Snackbar.make(view, "Start service", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()

        val intent = Intent(this, MyStartedService::class.java)
        intent.putExtra(MyStartedService.SLEEP_TIME, 10L)
        //intent.putExtra(MyStartedService.RECEIVER, )
        startService(intent)
    }
    fun stopTheStartedService(view : View) {
        Snackbar.make(view, "Stop service", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        val intent = Intent(this, MyStartedService::class.java)
        stopService(intent)
    }

    fun startTheIntentService(view : View) {
        Snackbar.make(view, "Start intent service", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()

        val myResultReceiver = MyResultReceiver(null)

        val intent = Intent(this, MyIntentService::class.java)
        intent.putExtra(MyStartedService.SLEEP_TIME, 10L)
        intent.putExtra(MyStartedService.RECEIVER, myResultReceiver)
        startService(intent)
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
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }


    inner class MyResultReceiver(handler:Handler?) : ResultReceiver(handler) {

        override fun onReceiveResult(resultCode: Int, resultData: Bundle?) {
            Log.i("MyResultReceiver", "onReceiveResult, Thread name = ${Thread.currentThread().name}")
            super.onReceiveResult(resultCode, resultData)
            if(resultCode == MyIntentService.OK_CODE && resultData != null) {
                val result = resultData.getString(MyIntentService.INTENT_RESULT)

                handler.post {
                    Log.i("Handler", "Handler, Thread name = ${Thread.currentThread().name}")
                    intentServiceTextView.text = result
                }

            }
        }
    }
}
