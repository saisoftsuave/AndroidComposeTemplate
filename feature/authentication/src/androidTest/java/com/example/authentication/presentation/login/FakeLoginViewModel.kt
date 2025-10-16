
package com.example.authentication.presentation.login

import androidx.lifecycle.ViewModel
import com.example.authentication.usecases.ValidateEmail
import com.example.authentication.usecases.ValidatePassword
import com.example.datastore.UserPreferences
import com.example.network.auth.repository.AuthRepository
import com.example.ui.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class FakeLoginViewModel @Inject constructor(
    validateEmail: ValidateEmail,
    validatePassword: ValidatePassword,
    authRepository: AuthRepository,
    dataStore: UserPreferences
) : LoginViewModel(validateEmail, validatePassword, authRepository, dataStore) {

    private val _loginState = MutableStateFlow<UiState<com.example.network.auth.model.LoginResponse>>(UiState.Idle)
    override val loginState: StateFlow<UiState<com.example.network.auth.model.LoginResponse>> = _loginState

    fun setLoginState(state: UiState<com.example.network.auth.model.LoginResponse>) {
        _loginState.value = state
    }
}
