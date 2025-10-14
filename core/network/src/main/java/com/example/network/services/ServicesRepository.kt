package com.example.network.services

import com.example.network.ApiResponse
import com.example.network.BaseResponse
import com.example.network.services.model.ServiceDto

import kotlinx.coroutines.flow.Flow

interface ServicesRepository {
    suspend fun getServices(): Flow<ApiResponse<BaseResponse<List<ServiceDto>>>>
}