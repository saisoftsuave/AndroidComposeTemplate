package com.example.network.services.api

import com.example.network.BaseResponse
import com.example.network.services.model.ServiceDto
import retrofit2.http.GET

interface ServicesService {
    @GET("services") // This would be the actual endpoint
    suspend fun getServices(): BaseResponse<List<ServiceDto>>
}