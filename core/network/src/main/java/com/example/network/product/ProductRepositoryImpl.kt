package com.example.network.product

import com.example.network.ApiResponse
import com.example.network.BaseRepository
import com.example.network.product.api.ProductService
import com.example.network.product.model.Product
import kotlinx.coroutines.flow.Flow

class ProductRepositoryImpl(
    val productService: ProductService
) : ProductRepository, BaseRepository() {
    override suspend fun getProductList(): Flow<ApiResponse<List<Product>>> {
        return apiCall { productService.getProducts() }
    }
}