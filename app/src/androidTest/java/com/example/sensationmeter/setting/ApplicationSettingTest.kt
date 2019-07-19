package com.example.sensationmeter.setting

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

class ApplicationSettingTest {

    private lateinit var instrumentationContext: Context
    private lateinit var application: ApplicationSetting

    @Before
    fun setup() {
        instrumentationContext = InstrumentationRegistry.getInstrumentation().context
        application = ApplicationSetting(instrumentationContext)
    }

    @Test
    fun getOriginalPassword() {
        val password = application.getPassword()
        assertEquals(password, "vSU1F77ydXl53O5om1CT0Q==")
    }

    @Test
    fun getNewlySetPassword() {
        application.setPassword("newPassword")
        val password = application.getPassword()
        assertEquals(AESEncryption.decrypt(password), "newPassword")
    }

    @Test
    fun sensationValue() {
        var value = application.getSensationVal()
        assertEquals(value, 0)

        application.setSensationVal(20)
        value = application.getSensationVal()
        assertEquals(value, 20)
    }
}