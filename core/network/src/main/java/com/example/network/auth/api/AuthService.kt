package com.example.network.auth.api

import com.example.network.auth.model.LoginResponse
import com.example.network.auth.model.LoginRequest
import com.example.network.auth.model.TestResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthService {
    @GET("/")
    suspend fun root(): TestResponse

    @POST("/user/signin")
    suspend fun login(@Body request: LoginRequest): LoginResponse

}