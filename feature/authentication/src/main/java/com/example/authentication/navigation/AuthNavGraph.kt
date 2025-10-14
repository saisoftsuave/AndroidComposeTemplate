package com.example.authentication.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.authentication.presentation.login.LoginScreen
import com.example.authentication.presentation.signup.SignUpScreen

fun NavGraphBuilder.authNavGraph(navController: NavHostController, onLoginSuccess: () -> Unit) {
    navigation<Authentication>(
        startDestination = LoginRoute
    ) {
        composable<LoginRoute> {
            LoginScreen(
                onLoginSuccess = onLoginSuccess,
                onNavigateToSignUp = { navController.navigate(SignUpRoute) }
            )
        }
        composable<SignUpRoute> {
            SignUpScreen(
                onSignUpSuccess = onLoginSuccess,
                onNavigateToLogin = { navController.navigateUp() }
            )
        }
    }
}