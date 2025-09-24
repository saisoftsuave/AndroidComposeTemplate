package com.example.network.product.api

import com.example.network.BaseResponse
import com.example.network.product.model.Product
import retrofit2.http.GET

interface ProductService {
    @GET("/products/")
    suspend fun getProducts(): BaseResponse<List<Product>>

}