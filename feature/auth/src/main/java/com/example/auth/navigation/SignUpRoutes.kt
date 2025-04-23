package com.example.auth.navigation


import androidx.navigation.NavController
import androidx.navigation.NavOptions
import kotlinx.serialization.Serializable

@Serializable
object SignUpRoute

fun NavController.navigateToSignUp(navOptions: NavOptions? = null) =
    navigate(route = SignUpRoute, navOptions)