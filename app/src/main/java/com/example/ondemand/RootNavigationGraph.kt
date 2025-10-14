package com.example.ondemand

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.authentication.navigation.Authentication
import com.example.authentication.navigation.Dashboard
import com.example.authentication.navigation.authNavGraph
import com.example.datastore.UserPreferences
import com.example.services.MainScreen
import kotlinx.coroutines.flow.first
import kotlinx.serialization.Serializable

@Serializable
object Loading

@Composable
fun RootNavigationGraph(navController: NavHostController, datastore: UserPreferences) {
    NavHost(
        navController = navController,
        startDestination = Loading
    ) {
        composable<Loading> {
            LaunchedEffect(Unit) {
                val token = datastore.accessToken.first()
                if (token.isNotEmpty()) {
                    navController.navigate(Dashboard) {
                        popUpTo(Loading) { inclusive = true }
                    }
                } else {
                    navController.navigate(Authentication) {
                        popUpTo(Loading) { inclusive = true }
                    }
                }
            }
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        authNavGraph(
            navController = navController,
            onLoginSuccess = {
                navController.navigate(Dashboard) {
                    popUpTo(0) { inclusive = true }
                    launchSingleTop = true
                }
            }
        )
        composable<Dashboard> {
            MainScreen()
        }
    }
}