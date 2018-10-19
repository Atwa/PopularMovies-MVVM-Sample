/*
 * *
 *  * Created by Ahmed Atwa on 19/10/2018
 *  * Copyright (c) 19/10/2018 . All rights reserved.
 *  *
 */

package ahmed.atwa.popularmovies

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
        assertEquals("ahmed.atwa.popularmovies", appContext.packageName)
    }
}
