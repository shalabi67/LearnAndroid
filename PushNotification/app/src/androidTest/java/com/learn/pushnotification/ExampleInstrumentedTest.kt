package com.learn.pushnotification

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.learn.pushnotification", appContext.packageName)
    }
}

/*
{
	"to":"futfJvStqFM:APA91bEIL1df9Rdtq7HEJPZSa7i5a1bzuqSgL_gsOL__uLxDWQhgukMAgJlcB8Kox9bAKB1p_U2yJmmsh_3-InlMbTfw78C-K5idfoeO1THJPD4i5ggM0vZbTxeSaRte_o0huYBzf4T0",
	"data": {
		"title" : "hello",
		"message": "my mwssage"
	}
}
 */