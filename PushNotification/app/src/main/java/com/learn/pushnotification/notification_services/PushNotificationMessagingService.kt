package com.learn.pushnotification.notification_services

import android.app.NotificationManager
import android.content.Context
import android.media.RingtoneManager
import android.support.v7.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.learn.pushnotification.R
import java.util.*


/**
 * Created by mohammad on 11/8/2017.
 */
class PushNotificationMessagingService : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)
        if(remoteMessage == null)
            return

        val notificationId = Random().nextInt(6000)
        val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(remoteMessage.data.get("title"))
                .setContentTitle(remoteMessage.data.get("message"))
                .setAutoCancel(true)
                .setSound(soundUri)
        val notificationManager : NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(notificationId, notificationBuilder.build());
    }
}