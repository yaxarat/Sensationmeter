package com.example.sensationmeter.setting

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class UserInformationTest {

    private lateinit var instrumentationContext: Context
    private lateinit var user: UserInformation

    @Before
    fun setup() {
        instrumentationContext = InstrumentationRegistry.getInstrumentation().context
        user = UserInformation(instrumentationContext)
    }

    @Test
    fun name() {
        var name = user.getName()
        assertEquals(name, "User_Name_Not_Set")

        user.setName("Jake")
        name = user.getName()
        assertEquals(name, "Jake")
    }
}