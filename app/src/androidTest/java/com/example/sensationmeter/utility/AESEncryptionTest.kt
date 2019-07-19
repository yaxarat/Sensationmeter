package com.example.sensationmeter.utility

import org.junit.Test
import org.junit.Assert.*

class AESEncryptionTest {
    private val encrypted = AESEncryption.encrypt("Password")
    private val decrypted = AESEncryption.decrypt(encrypted)

    @Test
    fun encrypt() {
        assertFalse(encrypted == "Password")
    }

    @Test
    fun decrypt() {
        assertTrue(decrypted == "Password")
    }
}