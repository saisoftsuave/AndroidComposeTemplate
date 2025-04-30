package com.example.auth.usecases

class ValidatePassword {
    fun execute(password: String): ValidationResult {
        return when {
            password.isBlank() -> {
                ValidationResult(
                    isSuccessful = false,
                    errorMessage = "Password cannot be blank."
                )
            }
            password.length < 8 -> {
                ValidationResult(
                    isSuccessful = false,
                    errorMessage = "Password should be at least 8 characters long."
                )
            }
            !password.any { it.isDigit() } -> {
                ValidationResult(
                    isSuccessful = false,
                    errorMessage = "Password must contain at least one digit."
                )
            }
            !password.any { it.isUpperCase() } -> {
                ValidationResult(
                    isSuccessful = false,
                    errorMessage = "Password must contain at least one uppercase letter."
                )
            }
            !password.any { it.isLowerCase() } -> {
                ValidationResult(
                    isSuccessful = false,
                    errorMessage = "Password must contain at least one lowercase letter."
                )
            }
            !password.any { !it.isLetterOrDigit() } -> {
                ValidationResult(
                    isSuccessful = false,
                    errorMessage = "Password must contain at least one special character."
                )
            }
            else -> {
                ValidationResult(
                    isSuccessful = true,
                    errorMessage = ""
                )
            }
        }
    }
}
