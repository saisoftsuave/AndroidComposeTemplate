package com.example.authentication.presentation.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.authentication.R
import com.example.ui.ImageButton
import com.example.ui.InputBox
import com.example.ui.PrimaryButton
import com.example.ui.utils.UiState

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier, onSignUpSuccess: () -> Unit, onNavigateToLogin: () -> Unit
) {
    val signUpViewModel: SignUpViewModel = hiltViewModel()
    val isLoading = signUpViewModel.isLoading.collectAsState()
    val signUpState by signUpViewModel.signUpState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = signUpState) {
        if (signUpState is UiState.Success) {
            onSignUpSuccess()
        }
    }

    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(R.drawable.app_icon),
                contentDescription = "app icon",
                modifier = Modifier.size(72.dp)
            )
            Text(
                "Create Account",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.SemiBold)
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            InputBox(
                supportingText = "Email",
                internalState = signUpViewModel.email,
                onValueChange = {
                    signUpViewModel.onEvent(SignUpScreenEvent.EmailChanged(it))
                }
            )
            InputBox(
                supportingText = "Password",
                internalState = signUpViewModel.password,
                keyboardType = KeyboardType.Password,
                onValueChange = {
                    signUpViewModel.onEvent(SignUpScreenEvent.PasswordChanged(it))
                }
            )
            InputBox(
                supportingText = "Confirm Password",
                internalState = signUpViewModel.confirmPassword,
                keyboardType = KeyboardType.Password,
                onValueChange = {
                    signUpViewModel.onEvent(SignUpScreenEvent.ConfirmPasswordChanged(it))
                }
            )
            PrimaryButton("Sign up", isLoading = isLoading.value) {
                signUpViewModel.onEvent(SignUpScreenEvent.SignUp)
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            HorizontalDivider(
                modifier = Modifier.weight(1f)
            )
            Text(
                "Or",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            HorizontalDivider(
                modifier = Modifier.weight(1f)
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ImageButton(
                buttonText = "Google",
                modifier = Modifier.weight(1f),
                iconDrawable = R.drawable.google_social_icon,
            ) {}

            ImageButton(
                buttonText = "Apple",
                modifier = Modifier.weight(1f),
                iconDrawable = R.drawable.apple_social_icon,
            ) {}
        }
        Text(
            "Already have an account? Sign in",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .clickable { onNavigateToLogin() },
            textAlign = TextAlign.Center
        )
    }

    when (signUpState) {
        is UiState.Error -> {
            Text(
                text = "Error: ${(signUpState as UiState.Error).message}",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(16.dp)
            )
        }

        else -> {}
    }
}
