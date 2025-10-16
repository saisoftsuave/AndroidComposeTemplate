    package com.example.network.auth.repository

    import com.example.network.ApiResponse
    import com.example.network.BaseResponse
    import com.example.network.auth.api.AuthService
    import com.example.network.auth.model.LoginRequest
    import com.example.network.auth.model.LoginResponse
    import kotlinx.coroutines.test.TestScope
    import kotlinx.coroutines.test.runTest
    import org.junit.After
    import org.junit.Assert.assertEquals
    import org.junit.Assert.assertNotNull
    import org.junit.Assert.fail
    import org.junit.Before
    import org.junit.Test
    import org.mockito.Mockito
    import org.mockito.Mockito.mock


    class AuthRepositoryImplTest {


         var authService: AuthService = mock()

        lateinit var authRepositoryImpl: AuthRepositoryImpl

        val accessToken = "access_token"
        val refreshToken = "refresh_token"

        val testCoroutineScope by lazy {
            TestScope()
        }


        @Before
        fun setUp() {
            authRepositoryImpl = AuthRepositoryImpl(authService)
        }


        @After
        fun tearDown() {
        }

        @Test
        fun test_login_success() = runTest {
            val expectedResponse = BaseResponse(
                "true",
                "Success",
                LoginResponse(
                    message = "Success",
                    refreshToken = refreshToken,
                    accessToken = accessToken,
                )
            )
            // Mock only the service
            Mockito.`when`(authService.login(LoginRequest("email", "password")))
                .thenReturn(expectedResponse)

            authRepositoryImpl.login("email", "password").collect {
                when (it) {
                    is ApiResponse.Error -> fail("Expected success but got error: ${it.message}")
                    ApiResponse.Loading -> { /* Optionally handle loading state */ }
                    is ApiResponse.Success -> {
                        val data = it.data
                        assertNotNull(data)
                        assertEquals(accessToken, data.data?.accessToken)
                        assertEquals(refreshToken, data.data?.refreshToken)
                        assertEquals("Success", data.message)
                    }
                }
            }
        }

        @Test
        fun test_login_error() = runTest {
            val errorMessage = "Invalid credentials"
            Mockito.`when`(authService.login(LoginRequest("email", "password")))
                .thenThrow(RuntimeException(errorMessage))

            authRepositoryImpl.login("email", "password").collect {
                when(it){
                    is ApiResponse.Error -> assertEquals("Unexpected error: $errorMessage", it.message)
                    ApiResponse.Loading -> { /* Optionally handle loading state */ }
                    is ApiResponse.Success -> fail("Expected error but got success")
                }
            }
        }

        @Test
        fun test_signUp_success() = runTest {
            val expectedResponse = BaseResponse(
                "true",
                "Success",
                LoginResponse(
                    message = "Success",
                    refreshToken = refreshToken,
                    accessToken = accessToken,
                )
            )
            Mockito.`when`(authService.signUp(LoginRequest("email", "password")))
                .thenReturn(expectedResponse)

            authRepositoryImpl.signUp("email", "password").collect {
                when (it) {
                    is ApiResponse.Error -> fail("Expected success but got error: ${it.message}")
                    ApiResponse.Loading -> { /* Optionally handle loading state */ }
                    is ApiResponse.Success -> {
                        val data = it.data
                        assertNotNull(data)
                        assertEquals(accessToken, data.data?.accessToken)
                        assertEquals(refreshToken, data.data?.refreshToken)
                        assertEquals("Success", data.message)
                    }
                }
            }
        }

        @Test
        fun test_signUp_error() = runTest {
            val errorMessage = "Email already exists"
            Mockito.`when`(authService.signUp(LoginRequest("email", "password")))
                .thenThrow(RuntimeException(errorMessage))

            authRepositoryImpl.signUp("email", "password").collect {
                when (it) {
                    is ApiResponse.Error -> assertEquals("Unexpected error: $errorMessage", it.message)
                    ApiResponse.Loading -> { /* Optionally handle loading state */ }
                    is ApiResponse.Success -> fail("Expected error but got success")
                }
            }
        }

        @Test
        fun test_testServer_success() = runTest {
            val expectedResponse = com.example.network.auth.model.TestResponse("true","Server OK")
            Mockito.`when`(authService.root()).thenReturn(expectedResponse)

            authRepositoryImpl.testServer().collect {
                when (it) {
                    is ApiResponse.Error -> fail("Expected success but got error: ${it.message}")
                    ApiResponse.Loading -> { /* Optionally handle loading state */ }
                    is ApiResponse.Success -> {
                        val data = it.data
                        assertNotNull(data)
                        assertEquals("Server OK", data.message)
                    }
                    }
                }
            }

        @Test
        fun test_testServer_error() = runTest {
            val errorMessage = "Server not reachable"
            Mockito.`when`(authService.root()).thenThrow(RuntimeException(errorMessage))

            authRepositoryImpl.testServer().collect {
                when (it) {
                    is ApiResponse.Error -> assertEquals("Unexpected error: $errorMessage", it.message)
                    ApiResponse.Loading -> { /* Optionally handle loading state */ }
                    is ApiResponse.Success -> fail("Expected error but got success")
                }
            }
        }
    }