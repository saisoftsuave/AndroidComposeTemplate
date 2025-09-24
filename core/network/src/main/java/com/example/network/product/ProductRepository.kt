package com.example.network.product

import com.example.network.ApiResponse
import com.example.network.BaseResponse
import com.example.network.product.model.Product

import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun getProductList(): Flow<ApiResponse<BaseResponse<List<Product>>>>
}