
package com.example.authentication.usecases

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ValidatePasswordTest {

    private lateinit var validatePassword: ValidatePassword

    @Before
    fun setUp() {
        validatePassword = ValidatePassword()
    }

    @Test
    fun `empty password returns error`() {
        val result = validatePassword.execute("")
        assertEquals("Password cannot be blank.", result.errorMessage)
        assertEquals(false, result.isSuccessful)
    }

    @Test
    fun `password too short returns error`() {
        val result = validatePassword.execute("12345")
        assertEquals("Password should be at least 8 characters long.", result.errorMessage)
        assertEquals(false, result.isSuccessful)
    }

    @Test
    fun `password without digit returns error`() {
        val result = validatePassword.execute("abcdefghi")
        assertEquals("Password must contain at least one digit.", result.errorMessage)
        assertEquals(false, result.isSuccessful)
    }

    @Test
    fun `password without uppercase letter returns error`() {
        val result = validatePassword.execute("abcdefghi1")
        assertEquals("Password must contain at least one uppercase letter.", result.errorMessage)
        assertEquals(false, result.isSuccessful)
    }

    @Test
    fun `password without lowercase letter returns error`() {
        val result = validatePassword.execute("ABCDEFGHI1")
        assertEquals("Password must contain at least one lowercase letter.", result.errorMessage)
        assertEquals(false, result.isSuccessful)
    }

    @Test
    fun `password without special character returns error`() {
        val result = validatePassword.execute("Abcdefghi1")
        assertEquals("Password must contain at least one special character.", result.errorMessage)
        assertEquals(false, result.isSuccessful)
    }

    @Test
    fun `passwords do not match returns error`() {
        val result = validatePassword.execute("Abcdefghi1!", "Abcdefghi1@")
        assertEquals("Passwords do not match.", result.errorMessage)
        assertEquals(false, result.isSuccessful)
    }

    @Test
    fun `valid password returns success`() {
        val result = validatePassword.execute("Abcdefghi1!")
        assertEquals("", result.errorMessage)
        assertEquals(true, result.isSuccessful)
    }
}
