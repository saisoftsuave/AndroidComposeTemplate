package com.example.authentication.usecases

import kotlin.text.isBlank

class ValidateEmail {
    fun execute(mobile: String): ValidationResult {
        return if (mobile.isBlank()) {
            ValidationResult(isSuccessful = false, errorMessage = "Mobile number cannot be blank.")
        } else if (mobile.length <= 9) {
            ValidationResult(
                isSuccessful = false,
                errorMessage = "Entered mobile number is invalid."
            )
        } else {
            ValidationResult(isSuccessful = true, errorMessage = "")
        }
    }
}