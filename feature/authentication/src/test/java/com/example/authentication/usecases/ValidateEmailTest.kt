
package com.example.authentication.usecases

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ValidateEmailTest {

    private lateinit var validateEmail: ValidateEmail

    @Before
    fun setUp() {
        validateEmail = ValidateEmail()
    }

    @Test
    fun `empty mobile returns error`() {
        val result = validateEmail.execute("")
        assertEquals("Mobile number cannot be blank.", result.errorMessage)
        assertEquals(false, result.isSuccessful)
    }

    @Test
    fun `invalid mobile format returns error`() {
        val result = validateEmail.execute("12345")
        assertEquals("Entered mobile number is invalid.", result.errorMessage)
        assertEquals(false, result.isSuccessful)
    }

    @Test
    fun `valid mobile returns success`() {
        val result = validateEmail.execute("1234567890")
        assertEquals("", result.errorMessage)
        assertEquals(true, result.isSuccessful)
    }
}
