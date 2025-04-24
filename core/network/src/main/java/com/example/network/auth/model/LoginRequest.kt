package com.example.network.auth.model

data class LoginRequest(
    val email: String,
    val password: String
)


data class VerifyOTPRequest(
    val otp: String,
    val mobileNumber: String
)