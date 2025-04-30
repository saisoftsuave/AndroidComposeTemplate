package com.example.auth.usecases

data class ValidationResult(
    val isSuccessful: Boolean = false,
    val errorMessage: String = ""
)