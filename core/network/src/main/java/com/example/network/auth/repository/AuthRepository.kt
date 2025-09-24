package com.example.network.auth.repository

import com.example.network.ApiResponse
import com.example.network.BaseResponse
import com.example.network.auth.model.LoginResponse
import com.example.network.auth.model.TestResponse
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun testServer(): Flow<ApiResponse<TestResponse>>
    fun login(email: String, password: String): Flow<ApiResponse<BaseResponse<LoginResponse>>>
}