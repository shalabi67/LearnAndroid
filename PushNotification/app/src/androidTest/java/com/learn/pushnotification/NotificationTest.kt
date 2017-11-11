package com.learn.pushnotification

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.learn.pushnotification.tests.HttpRequest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.HashMap

/**
 * Created by mohammad on 11/11/2017.
 */
@RunWith(AndroidJUnit4::class)
class NotificationTest {
    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testNotificationRecieved() {
        System.setProperty("javax.net.debug","all");
        val httpRequest = HttpRequest()
        val header = HashMap<String, String>()
        header.put("Authorization", "key=AAAAmXtVm8M:APA91bEyt-4WOI4zGpILq3tWuU8XLg4OKUl6Qocjwb86X794HcrtL2kvNuoIz5Qb2AA7u6Pr5_PS4uyr5be8ersuuQnmG_hzqTGDJxj3Zq3WZVVXiT1oHSdHFs74YzXi9TB0rFnjFfPh")
        header.put("Content-Type", "application/json")

        val jsonData = "{\n" +
                "\t\"to\":\"f5ldbppoISU:APA91bFC7O7vGEF2Um6VUpnKR9XOf19TMIRddSj2TyRUmGJFOnLOQsdvPrIIbU7W0FV7Iz3eP4qaQZQKJ_5akV7TeSZFV5KfJGzKStg6TVKUCVR7qS2IoFYrsV_07Al7BKQiddSgKx6Y\n\",\n" +
                "\t\"data\": {\n" +
                "\t\t\"id\" : \"activity\",\n" +
                "\t\t\"title\" : \"hello\",\n" +
                "\t\t\"message\": \"my mwssage\"\n" +
                "\t}\n" +
                "}"
        httpRequest.send("https://fcm.googleapis.com/fcm/send", header, jsonData)
    }

}