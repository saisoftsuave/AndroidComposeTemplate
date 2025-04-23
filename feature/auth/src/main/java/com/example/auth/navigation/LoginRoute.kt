package com.example.auth.navigation


import androidx.navigation.NavController
import androidx.navigation.NavOptions
import kotlinx.serialization.Serializable

@Serializable
object LoginRoute

fun NavController.navigateToLogin(navOptions: NavOptions? = null) =
    navigate(route = LoginRoute, navOptions)