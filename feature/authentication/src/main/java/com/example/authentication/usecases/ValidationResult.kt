package com.example.authentication.usecases

data class ValidationResult(
    val isSuccessful: Boolean = false,
    val errorMessage: String = ""
)