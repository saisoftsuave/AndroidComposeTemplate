package com.example.services

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.network.ApiResponse
import com.example.network.services.ServicesRepository
import com.example.network.services.model.ServiceDto
import com.example.services.mapper.toService
import com.example.ui.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val servicesRepository: ServicesRepository
) : ViewModel() {

    private val _mainState = MutableStateFlow<UiState<List<Service>>>(UiState.Idle)
    val mainState: StateFlow<UiState<List<Service>>> = _mainState.asStateFlow()

    init {
        getServices()
    }

    fun getServices() {
        viewModelScope.launch {
            servicesRepository.getServices().collectLatest { result ->
                when (result) {
                    is ApiResponse.Loading -> {
                        _mainState.value = UiState.Loading
                    }
                    is ApiResponse.Error -> {
                        _mainState.value = UiState.Error(result.message)
                    }
                    is ApiResponse.Success -> {
                        // Convert DTOs to domain models
                        val baseResponse = result.data  // This is BaseResponse<List<ServiceDto>>
                        val serviceDtos = baseResponse?.data  // This is List<ServiceDto>
                        val services = if (serviceDtos != null) {
                            serviceDtos.map { dto -> dto.toService() }
                        } else {
                            emptyList()
                        }
                        _mainState.value = UiState.Success(services)
                    }
                }
            }
        }
    }

    fun retry() {
        getServices()
    }
}