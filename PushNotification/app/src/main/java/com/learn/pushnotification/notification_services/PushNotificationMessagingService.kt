package com.learn.pushnotification.notification_services

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.support.v4.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.learn.pushnotification.R
import java.util.*




/**
 * Created by mohammad on 11/8/2017.
 */
class PushNotificationMessagingService : FirebaseMessagingService() {
    companion object {
        val NOTIFICATION_ID = "NOTIFICATION_ID"
    }
    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)
        if(remoteMessage == null)
            return

        val id : String? = remoteMessage.data.get("id")
        if(id == null)
            return

        val notificationId = Random().nextInt(6000)
        val notificationIntent: Intent = createActivityIntent()

        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        val pendingIntent = createNotificationIntent(id, notificationId)//PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_ONE_SHOT);

        val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val action = NotificationCompat.Action.Builder(R.drawable.fav,
                getString(R.string.notification_like_button),
                createLikeNotificationPendingIntent(notificationId)).build()
        val bitmap =  BitmapFactory.decodeResource(this.getResources(), R.drawable.images)
        val notificationBuilder = NotificationCompat.Builder(this)
                .setLargeIcon(bitmap)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(remoteMessage.data.get("title"))
                .setContentText(remoteMessage.data.get("message"))
                .setStyle(NotificationCompat.BigPictureStyle()
                        .setSummaryText(remoteMessage.getData().get("message"))
                        .bigPicture(bitmap))
                .setAutoCancel(true)
                .setSound(soundUri)
                .addAction(action)
                .setContentIntent(pendingIntent)
        val notificationManager : NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(notificationId, notificationBuilder.build());
    }


    //may be we can use this to call service or call activity
    private fun createNotificationIntent(id: String, notificationId : Int): PendingIntent {
        if(id == "activity")
            return  PendingIntent.getActivity(this, 0, createActivityIntent(), PendingIntent.FLAG_ONE_SHOT);
        else
            return PendingIntent.getService(this, 0, createNotificationServiceIntent(notificationId), 0)
    }


    private fun createActivityIntent() : Intent {
        val notificationIntent: Intent
        if(NotificationDisplayActivity.IS_RUNNING){
            notificationIntent =  Intent(this, NotificationDisplayActivity::class.java)
        }
        else{
            notificationIntent = Intent(this, NotificationDisplayActivity::class.java)
        }

        return notificationIntent
    }
    private fun createNotificationServiceIntent(notificationId : Int) : Intent {
        val pushServiceIntent = Intent(this, NotificationService::class.java)
        pushServiceIntent.putExtra(NOTIFICATION_ID, notificationId)

        return pushServiceIntent
    }

    private fun createLikeNotificationPendingIntent(notificationId : Int) : PendingIntent {
        /*
        return PendingIntent.getActivity(this,
                5,
                Intent(this, NotificationDisplayActivity::class.java), 0)
                */

        return PendingIntent.getService(this,
                notificationId + 1,
                createNotificationServiceIntent(notificationId),
                PendingIntent.FLAG_ONE_SHOT
                )

    }
}