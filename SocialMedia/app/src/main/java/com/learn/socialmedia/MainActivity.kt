package com.learn.socialmedia

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import com.facebook.share.widget.LikeView
import com.facebook.share.widget.ShareButton
import com.facebook.share.model.ShareLinkContent
import com.facebook.share.widget.ShareDialog
import com.facebook.share.model.SharePhoto
import com.facebook.share.model.ShareContent
import com.facebook.share.model.SharePhotoContent
import android.graphics.drawable.BitmapDrawable
import com.learn.socialmedia.R.id.imageView












class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

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

        val facebookLikeView = findViewById<LikeView>(R.id.likeView) as LikeView
        facebookLikeView.setLikeViewStyle(LikeView.Style.STANDARD)
        facebookLikeView.setAuxiliaryViewPosition(LikeView.AuxiliaryViewPosition.INLINE)

        facebookLikeView.setObjectIdAndType(
                "https://www.facebook.com/AnimatedGreetingCards/",
                LikeView.ObjectType.OPEN_GRAPH)


        val shareButton = findViewById<ShareButton>(R.id.share_btn)
        val content = ShareLinkContent.Builder()
                .setContentUrl(Uri.parse("https://play.google.com/store/apps/details?id=com.ne3am.avatarproject"))
                .setContentTitle("Animated greeting cards")
                .setContentDescription("ebardy provides nice animated greeting cards.")
                .build()
        shareButton.shareContent = content


        /*
        val mediaShareButton = findViewById<ShareButton>(R.id.media_share_btn)

        val imageView = findViewById<ImageView>(R.id.imageView2)
        imageView.buildDrawingCache()
        val bitmap = imageView.drawingCache
        var imageContent = SharePhoto.Builder()
               // .setImageUrl(Uri.parse("http://www.ebardy.com/ProjectImages/2014/a0634454-c618-4da8-a783-959f8c8b6e56/project.gif"))
                .setBitmap(bitmap)
                .build();
        var imageContent1 = SharePhoto.Builder()
                .setImageUrl(Uri.parse("http://www.ebardy.com/ProjectImages/2014/4d50bceb-1803-441b-b78f-afd3d6823d56/project.gif"))
                .build();
        val photoContent = SharePhotoContent.Builder()
                //.setContentUrl(Uri.parse("https://play.google.com/store/apps/details?id=com.ne3am.avatarproject"))
                .addPhoto(imageContent)
               // .addPhoto(imageContent1)
                .build()


        mediaShareButton.shareContent = photoContent
        */

        /*
        val sharedialog =  ShareDialog(this)
        if (sharedialog.canShow(content)) {
            ShareDialog.show(this, photoContent);
        }
        */


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
            R.id.nav_login -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
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
}
