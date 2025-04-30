package com.example.ui

data class InputBoxState(
    var value: String = "",
    val error: String = "",
    val isValid: Boolean = false,
)
