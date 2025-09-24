package com.example.network

data class BaseResponse<T>(
    val status: String,
    val message: String,
    val data: T?
)