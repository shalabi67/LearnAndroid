package com.learn.pushnotification.http

import com.learn.pushnotification.tests.data.ApplicationData
import com.learn.pushnotification.tests.data.GoogleNotificationData
import com.learn.pushnotification.tests.HttpRequest
import org.junit.Test
import java.util.HashMap

/**
 * Created by mohammad on 11/11/2017.
 */
class NotificationTests {
    @Test
    fun sendRequest() {
        val httpRequest = HttpRequest()
        val header = HashMap<String, String>()
        header.put("Authorization", "key=AAAAmXtVm8M:APA91bEyt-4WOI4zGpILq3tWuU8XLg4OKUl6Qocjwb86X794HcrtL2kvNuoIz5Qb2AA7u6Pr5_PS4uyr5be8ersuuQnmG_hzqTGDJxj3Zq3WZVVXiT1oHSdHFs74YzXi9TB0rFnjFfPh")

        val notificationToken = "dzUmYXYikys:APA91bHhh0bfQv_yx8MvmF7eO1sEKKz40qtzzVDwsMri7-s_Q-rJTPWPWbBFD5HHzlGmSHXOHn94dQpgjVWeOi89sODamTj0jqDUxjzvAPfAzdtcjvqM5glIlIFe5Bs9KnFXBueyVJIb"
        val applicationData = ApplicationData("activity", "hello from test", "message from testing code")
        val googleNotificationData = GoogleNotificationData(notificationToken, applicationData)
        httpRequest.send("https://fcm.googleapis.com/fcm/send", header, googleNotificationData.toString())
    }
}