
package com.example.authentication.presentation.signup

import com.example.authentication.usecases.ValidateEmail
import com.example.authentication.usecases.ValidatePassword
import com.example.authentication.usecases.ValidationResult
import com.example.datastore.UserPreferences
import com.example.network.ApiResponse
import com.example.network.BaseResponse
import com.example.network.auth.model.LoginResponse
import com.example.network.auth.repository.AuthRepository
import com.example.ui.utils.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
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
class SignUpViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    @Mock
    private lateinit var validateEmail: ValidateEmail

    @Mock
    private lateinit var validatePassword: ValidatePassword

    @Mock
    private lateinit var authRepository: AuthRepository

    @Mock
    private lateinit var dataStore: UserPreferences

    private lateinit var viewModel: SignUpViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = SignUpViewModel(validateEmail, validatePassword, authRepository, dataStore)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `signUp success updates signUpState and saves tokens`() = runTest(testDispatcher) {
        val email = "test@example.com"
        val password = "password123"
        val accessToken = "accessToken"
        val refreshToken = "refreshToken"
        val loginResponse = LoginResponse(accessToken = accessToken, refreshToken = refreshToken, message = "Success")
        val baseResponse = BaseResponse(status = "Success", message = "Success", data = loginResponse)

        `when`(validateEmail.execute(email)).thenReturn(ValidationResult(isSuccessful = true))
        `when`(validatePassword.execute(password)).thenReturn(ValidationResult(isSuccessful = true))
        `when`(validatePassword.execute(password, password)).thenReturn(ValidationResult(isSuccessful = true))
        `when`(authRepository.signUp(email, password)).thenReturn(flowOf(ApiResponse.Loading, ApiResponse.Success(baseResponse)))

        viewModel.onEvent(SignUpScreenEvent.EmailChanged(email))
        viewModel.onEvent(SignUpScreenEvent.PasswordChanged(password))
        viewModel.onEvent(SignUpScreenEvent.ConfirmPasswordChanged(password))
        viewModel.onEvent(SignUpScreenEvent.SignUp)
        advanceUntilIdle()

        val signUpState = viewModel.signUpState.value
        assertEquals(UiState.Success(loginResponse), signUpState)

        runBlocking {
            verify(dataStore).updateAccessToken(accessToken)
            verify(dataStore).updateRefreshToken(refreshToken)
        }
    }

    @Test
    fun `signUp error updates signUpState`() = runTest(testDispatcher) {
        val email = "test@example.com"
        val password = "password123"
        val errorMessage = "An error occurred"

        `when`(validateEmail.execute(email)).thenReturn(ValidationResult(isSuccessful = true))
        `when`(validatePassword.execute(password)).thenReturn(ValidationResult(isSuccessful = true))
        `when`(validatePassword.execute(password, password)).thenReturn(ValidationResult(isSuccessful = true))
        `when`(authRepository.signUp(email, password)).thenReturn(flowOf(ApiResponse.Loading, ApiResponse.Error(errorMessage)))

        viewModel.onEvent(SignUpScreenEvent.EmailChanged(email))
        viewModel.onEvent(SignUpScreenEvent.PasswordChanged(password))
        viewModel.onEvent(SignUpScreenEvent.ConfirmPasswordChanged(password))
        viewModel.onEvent(SignUpScreenEvent.SignUp)
        advanceUntilIdle()

        val signUpState = viewModel.signUpState.value
        assertEquals(UiState.Error(errorMessage), signUpState)
    }
}
