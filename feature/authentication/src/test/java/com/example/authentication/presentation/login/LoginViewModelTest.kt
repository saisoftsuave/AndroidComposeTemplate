
package com.example.authentication.presentation.login

import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.advanceUntilIdle

import com.example.authentication.usecases.ValidateEmail
import com.example.authentication.usecases.ValidatePassword
import com.example.authentication.usecases.ValidationResult
import com.example.datastore.UserPreferences
import com.example.network.ApiResponse
import com.example.network.BaseResponse
import com.example.network.auth.model.LoginResponse
import com.example.network.auth.model.TestResponse
import com.example.network.auth.repository.AuthRepository
import com.example.ui.utils.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class LoginViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    @Mock
    private lateinit var validateEmail: ValidateEmail

    @Mock
    private lateinit var validatePassword: ValidatePassword

    @Mock
    private lateinit var authRepository: AuthRepository

    @Mock
    private lateinit var dataStore: UserPreferences

    private lateinit var viewModel: LoginViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        `when`(authRepository.testServer()).thenReturn(flowOf(ApiResponse.Success(TestResponse(status = "", message = ""))))
        viewModel = LoginViewModel(validateEmail, validatePassword, authRepository, dataStore)
        println("ViewModel initialized: $viewModel")
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `email changed event updates email state`() = runTest(testDispatcher) {
        val email = "test@example.com"
        `when`(validateEmail.execute(email)).thenReturn(ValidationResult(isSuccessful = true))

        viewModel.loginEvent(LoginScreenEvent.EmailChangedEvent(email))
        advanceUntilIdle()

        val state = viewModel.email.value
        println("Email state: $state")
        assertEquals(email, state.value)
        assertEquals(true, state.isValid)
    }

    @Test
    fun `password changed event updates password state`() = runTest(testDispatcher) {
        val password = "password123"
        `when`(validatePassword.execute(password)).thenReturn(ValidationResult(isSuccessful = true))

        viewModel.loginEvent(LoginScreenEvent.PasswordChangedEvent(password))
        advanceUntilIdle()

        val state = viewModel.password.value
        println("Password state: $state")
        assertEquals(password, state.value)
        assertEquals(true, state.isValid)
    }

    @Test
    fun `login success updates loginState and saves tokens`() = runTest(testDispatcher) {
        val email = "test@example.com"
        val password = "password123"
        val accessToken = "accessToken"
        val refreshToken = "refreshToken"
        val loginResponse = LoginResponse(accessToken = accessToken, refreshToken = refreshToken, message = "Success")
        val baseResponse = BaseResponse(status = "Success", message = "Success", data = loginResponse)

        `when`(validateEmail.execute(email)).thenReturn(ValidationResult(isSuccessful = true))
        `when`(validatePassword.execute(password)).thenReturn(ValidationResult(isSuccessful = true))
        `when`(authRepository.login(email, password)).thenReturn(flowOf(ApiResponse.Loading, ApiResponse.Success(baseResponse)))

        viewModel.loginEvent(LoginScreenEvent.EmailChangedEvent(email))
        viewModel.loginEvent(LoginScreenEvent.PasswordChangedEvent(password))
        viewModel.loginEvent(LoginScreenEvent.SignUpEvent())
        advanceUntilIdle()

        val loginState = viewModel.loginState.value
        println("Login state: $loginState")
        assertEquals(UiState.Success(loginResponse), loginState)

        runBlocking {
            verify(dataStore).updateAccessToken(accessToken)
            verify(dataStore).updateRefreshToken(refreshToken)
        }
    }

    @Test
    fun `login error updates loginState`() = runTest(testDispatcher) {
        val email = "test@example.com"
        val password = "password123"
        val errorMessage = "Invalid credentials"

        `when`(validateEmail.execute(email)).thenReturn(ValidationResult(isSuccessful = true))
        `when`(validatePassword.execute(password)).thenReturn(ValidationResult(isSuccessful = true))
        `when`(authRepository.login(email, password)).thenReturn(flowOf(ApiResponse.Loading, ApiResponse.Error(errorMessage)))

        viewModel.loginEvent(LoginScreenEvent.EmailChangedEvent(email))
        viewModel.loginEvent(LoginScreenEvent.PasswordChangedEvent(password))
        viewModel.loginEvent(LoginScreenEvent.SignUpEvent())
        advanceUntilIdle()

        val loginState = viewModel.loginState.value
        println("Login error state: $loginState")
        assertEquals(UiState.Error(errorMessage), loginState)
    }
}
