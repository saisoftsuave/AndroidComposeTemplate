package com.example.authentication.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authentication.usecases.ValidateEmail
import com.example.authentication.usecases.ValidatePassword
import com.example.datastore.UserPreferences
import com.example.network.ApiResponse
import com.example.network.auth.model.LoginResponse
import com.example.network.auth.repository.AuthRepository
import com.example.network.graphql.api.CountryService
import com.example.ui.InputBoxState
import com.example.ui.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


@HiltViewModel
open class LoginViewModel @Inject constructor(
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword,
    private val authRepository: AuthRepository,
    private val dataStore: UserPreferences,
    private val apolloService : CountryService
) : ViewModel() {

    val _email = MutableStateFlow(InputBoxState())
    val email: StateFlow<InputBoxState> = _email.asStateFlow()


    val _password = MutableStateFlow(InputBoxState())
    val password: StateFlow<InputBoxState> = _password.asStateFlow()

    private val _loginState = MutableStateFlow<UiState<LoginResponse>>(UiState.Idle)
    open val loginState: StateFlow<UiState<LoginResponse>> = _loginState.asStateFlow()

    val isLoading = MutableStateFlow(false)


    init {
        runBlocking {
            authRepository.testServer().collectLatest {
                println("test response + $it")
            }
        }
        viewModelScope.launch {
            dataStore.accessToken.collectLatest {
                println("Access token + $it")
            }
            dataStore.refreshToken.collectLatest {
                println("Refresh token + $it")
            }
        }
    }


    fun hitAPI(){
        viewModelScope.launch(Dispatchers.IO) {
            val countries = apolloService.getCountries()
            println("Countries : $countries")
        }
    }


    fun loginEvent(
        event: LoginScreenEvent
    ) {
        when (event) {
            is LoginScreenEvent.EmailChangedEvent -> {
                val emailValidationResult = validateEmail.execute(event.email)
                _email.value =
                    _email.value.copy(
                        error = emailValidationResult.errorMessage,
                        value = event.email,
                        isValid = emailValidationResult.isSuccessful
                    )
            }

            is LoginScreenEvent.PasswordChangedEvent -> {
                val otpValidationResult = validatePassword.execute(event.password)
                _password.value =
                    _password.value.copy(
                        error = otpValidationResult.errorMessage,
                        value = event.password,
                        isValid = otpValidationResult.isSuccessful
                    )
            }

            is LoginScreenEvent.SignUpEvent -> {
                if (_email.value.isValid && _password.value.isValid) {
                    login()
                } else {
                    validatePassword.execute(_password.value.value)
                    validateEmail.execute(_email.value.value)
                }
            }
        }
    }

    private fun login() {
        viewModelScope.launch {
            authRepository.login(email.value.value, password.value.value).collectLatest {
                when (it) {
                    is ApiResponse.Error -> {
                        _loginState.value = UiState.Error(it.message)
                        isLoading.value = false
                    }

                    ApiResponse.Loading -> {
                        _loginState.value = UiState.Loading
                        isLoading.value = true
                    }

                    is ApiResponse.Success -> {
                        _loginState.value = UiState.Success(it.data.data ?: LoginResponse(
                            accessToken = "",
                            refreshToken = "",
                            message = "null"
                        ))
                        isLoading.value = false
                        viewModelScope.launch(Dispatchers.IO) {
                            dataStore.updateAccessToken(it.data.data?.accessToken ?: "")
                            dataStore.updateRefreshToken(it.data.data?.refreshToken ?: "")
                        }
                    }
                }
            }
        }
    }
}


sealed class LoginScreenEvent() {
    class EmailChangedEvent(val email: String) : LoginScreenEvent()
    class PasswordChangedEvent(val password: String) : LoginScreenEvent()
    class SignUpEvent : LoginScreenEvent()
}