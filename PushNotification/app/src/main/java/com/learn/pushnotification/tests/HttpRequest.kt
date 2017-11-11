package com.learn.pushnotification.tests


import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.Charset
import java.io.*


/**
 * Created by mohammad on 11/11/2017.
 */
class HttpRequest {
    fun send(address : String, header: MutableMap<String, String>, jsonData : String) {
        val myURL = URL(address)
        val urlConnection = myURL.openConnection() as HttpURLConnection
        urlConnection.requestMethod = "POST"
        urlConnection.doOutput = true
        urlConnection.doInput = true
        urlConnection.setRequestProperty("Accept", "application/json");
        urlConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

        //add header
        for(key in header.keys) {
            urlConnection.setRequestProperty(key, header.get(key))
        }

        val dataOutputStream = DataOutputStream( urlConnection.outputStream)
        val jsonByteData = jsonData.toByteArray(Charset.forName("UTF-8"))
        dataOutputStream.write(jsonByteData)
        dataOutputStream.flush()
        dataOutputStream.close()
        urlConnection.outputStream.close()

        val statusCode = urlConnection.responseCode
        var error = ""
        if(statusCode != 200) {
            error = convertInputStreamToString(urlConnection.errorStream)

        }

       // val inputStream = BufferedInputStream(urlConnection.inputStream)
        //val response = convertInputStreamToString(inputStream)

        urlConnection.disconnect()

        //urlConnection.connect()
        //val userCredentials = "username:password"
        //val basicAuth = "Basic " + String(Base64().encode(userCredentials.toByteArray()))
        //myURLConnection.setRequestProperty("Authorization", basicAuth)

    }

    private fun convertInputStreamToString(inputStream: InputStream): String {
        val bufferedStream = BufferedReader(InputStreamReader(inputStream))
        val result = StringBuilder()
        var line: String?
        line = bufferedStream.readLine()
        while ( line != null) {
            result.append(line).append('\n')
            line = bufferedStream.readLine()
        }

        return result.toString()
    }
}