package com.example.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.network.ApiResponse
import com.example.network.product.ProductRepository
import com.example.network.product.model.Product
import com.example.ui.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _productsState = MutableStateFlow<UiState<List<Product>>>(UiState.Idle)
    val productsState: StateFlow<UiState<List<Product>>> = _productsState.asStateFlow()

    init {
        fetchProducts()
    }

    fun retry() {
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            _productsState.value = UiState.Loading
            productRepository.getProductList().collect { response ->
                when (response) {
                    is ApiResponse.Error -> {
                        _productsState.value = UiState.Error(response.message)
                    }
                    is ApiResponse.Loading -> {
                        _productsState.value = UiState.Loading
                    }
                    is ApiResponse.Success -> {
                        _productsState.value = UiState.Success(response.data.data ?: emptyList())
                    }
                }
            }
        }
    }
}