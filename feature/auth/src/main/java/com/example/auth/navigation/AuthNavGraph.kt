package com.example.auth.navigation


import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.auth.presentation.login.LoginScreen
import com.example.auth.presentation.signup.SignUpScreen


fun NavGraphBuilder.authNavGraph(onLoginSuccess: () -> Unit) {
    navigation<Authentication>(
        startDestination = LoginRoute
    ) {
        composable<LoginRoute> {
            LoginScreen(onLoginSuccess = onLoginSuccess)
        }
        composable<SignUpRoute> {
            SignUpScreen()
        }
    }
}