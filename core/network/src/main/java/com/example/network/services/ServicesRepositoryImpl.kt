package com.example.network.services

import com.example.network.ApiResponse
import com.example.network.BaseRepository
import com.example.network.BaseResponse
import com.example.network.services.api.ServicesService
import com.example.network.services.model.ServiceDto
import kotlinx.coroutines.flow.Flow

class ServicesRepositoryImpl(
    private val servicesService: ServicesService
) : ServicesRepository, BaseRepository() {
    override suspend fun getServices(): Flow<ApiResponse<BaseResponse<List<ServiceDto>>>> {
        return apiCall { 
            servicesService.getServices()
        }
    }
}