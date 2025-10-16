package com.example.authentication.presentation.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authentication.usecases.ValidateEmail
import com.example.authentication.usecases.ValidatePassword
import com.example.datastore.UserPreferences
import com.example.network.ApiResponse
import com.example.network.auth.model.LoginResponse
import com.example.network.auth.repository.AuthRepository
import com.example.ui.InputBoxState
import com.example.ui.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class SignUpViewModel @Inject constructor(
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword,
    private val authRepository: AuthRepository,
    private val dataStore: UserPreferences
) : ViewModel() {

    val _email = MutableStateFlow(InputBoxState())
    val email: StateFlow<InputBoxState> = _email.asStateFlow()

    val _password = MutableStateFlow(InputBoxState())
    val password: StateFlow<InputBoxState> = _password.asStateFlow()

    val _confirmPassword = MutableStateFlow(InputBoxState())
    val confirmPassword: StateFlow<InputBoxState> = _confirmPassword.asStateFlow()

    private val _signUpState = MutableStateFlow<UiState<LoginResponse>>(UiState.Idle)
    val signUpState: StateFlow<UiState<LoginResponse>> = _signUpState.asStateFlow()

    val isLoading = MutableStateFlow(false)

    fun onEvent(event: SignUpScreenEvent) {
        when (event) {
            is SignUpScreenEvent.EmailChanged -> {
                val emailValidationResult = validateEmail.execute(event.email)
                _email.value =
                    _email.value.copy(
                        error = emailValidationResult.errorMessage,
                        value = event.email,
                        isValid = emailValidationResult.isSuccessful
                    )
            }

            is SignUpScreenEvent.PasswordChanged -> {
                val passwordValidationResult = validatePassword.execute(event.password)
                _password.value =
                    _password.value.copy(
                        error = passwordValidationResult.errorMessage,
                        value = event.password,
                        isValid = passwordValidationResult.isSuccessful
                    )
            }

            is SignUpScreenEvent.ConfirmPasswordChanged -> {
                val confirmPasswordValidationResult = validatePassword.execute(event.confirmPassword, password.value.value)
                _confirmPassword.value =
                    _confirmPassword.value.copy(
                        error = confirmPasswordValidationResult.errorMessage,
                        value = event.confirmPassword,
                        isValid = confirmPasswordValidationResult.isSuccessful
                    )
            }

            is SignUpScreenEvent.SignUp -> {
                if (_email.value.isValid && _password.value.isValid && _confirmPassword.value.isValid) {
                    signUp()
                } else {
                    validatePassword.execute(_password.value.value)
                    validateEmail.execute(_email.value.value)
                }
            }
        }
    }

    private fun signUp() {
        viewModelScope.launch {
            authRepository.signUp(email.value.value, password.value.value).collectLatest {
                when (it) {
                    is ApiResponse.Error -> {
                        _signUpState.value = UiState.Error(it.message)
                        isLoading.value = false
                    }

                    ApiResponse.Loading -> {
                        _signUpState.value = UiState.Loading
                        isLoading.value = true
                    }

                    is ApiResponse.Success -> {
                        _signUpState.value = UiState.Success(it.data.data ?: LoginResponse(
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
