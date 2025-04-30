package com.example.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.network.ApiResponse
import com.example.network.product.ProductRepository
import com.example.network.product.model.Product
import com.example.ui.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _productsState =
        MutableStateFlow(
            listOf<Product>()
        )
    val productsState: StateFlow<List<Product>> = _productsState.asStateFlow()

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            productRepository.getProductList().collect {
                when (it) {
                    is ApiResponse.Error -> {
                        _isLoading.value = false
                    }

                    ApiResponse.Loading -> {
                        _isLoading.value = true
                    }

                    is ApiResponse.Success -> {
                        _productsState.value = it.data
                        _isLoading.value = false
                    }
                }
            }
        }
    }
}