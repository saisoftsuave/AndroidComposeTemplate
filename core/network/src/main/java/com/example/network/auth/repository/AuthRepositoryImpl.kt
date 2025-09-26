package com.example.network.auth.repository

import com.example.network.ApiResponse
import com.example.network.BaseRepository
import com.example.network.BaseResponse
import com.example.network.auth.model.LoginResponse
import com.example.network.auth.api.AuthService
import com.example.network.auth.model.LoginRequest
import com.example.network.auth.model.TestResponse
import kotlinx.coroutines.flow.Flow

class AuthRepositoryImpl(
    private val authService: AuthService
) : AuthRepository, BaseRepository() {
    override fun testServer(): Flow<ApiResponse<TestResponse>> {
        return apiCall { authService.root() }
    }

    override fun login(
        email: String,
        password: String
    ): Flow<ApiResponse<BaseResponse<LoginResponse>>> {
        return apiCall { authService.login(LoginRequest(email, password)) }
    }

    override fun signUp(
        email: String,
        password: String
    ): Flow<ApiResponse<BaseResponse<LoginResponse>>> {
        return apiCall { authService.signUp(LoginRequest(email, password)) }
    }

}