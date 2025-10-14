package com.example.auth.presentation.signup

sealed class SignUpScreenEvent {
    data class EmailChanged(val email: String) : SignUpScreenEvent()
    data class PasswordChanged(val password: String) : SignUpScreenEvent()
    data class ConfirmPasswordChanged(val confirmPassword: String) : SignUpScreenEvent()
    object SignUp : SignUpScreenEvent()
}
