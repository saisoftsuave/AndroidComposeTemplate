package com.example.authentication.presentation.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.authentication.R
import com.example.network.auth.model.LoginResponse
import com.example.ui.ImageButton
import com.example.ui.InputBox
import com.example.ui.PrimaryButton
import com.example.ui.utils.UiState


@Composable
fun LoginScreen(
    modifier: Modifier = Modifier, onLoginSuccess: () -> Unit, onNavigateToSignUp: () -> Unit
) {
    val loginViewModel: LoginViewModel = hiltViewModel()
    val isLoading = loginViewModel.isLoading.collectAsState()
    val loginState by loginViewModel.loginState.collectAsStateWithLifecycle()


    LaunchedEffect(key1 = loginState) {
        if (loginState is UiState.Success) {
            onLoginSuccess()
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
                "Welcome Back",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.SemiBold)
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {

            InputBox(
                supportingText = "Email",
                internalState = loginViewModel.email,
                onValueChange = {
                    loginViewModel.loginEvent(LoginScreenEvent.EmailChangedEvent(it))
                }
            )
            InputBox(
                supportingText = "Password",
                internalState = loginViewModel.password,
                keyboardType = KeyboardType.Password,
                onValueChange = {
                    loginViewModel.loginEvent(LoginScreenEvent.PasswordChangedEvent(it))
                }
            )
            Text(
                "Forgot password?",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                textAlign = TextAlign.End
            )
            PrimaryButton("Sign in", isLoading = isLoading.value) {
                loginViewModel.loginEvent(LoginScreenEvent.SignUpEvent())
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
            "Don\'t have account? Sign up",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .clickable { onNavigateToSignUp() },
            textAlign = TextAlign.Center
        )
    }

    when (loginState) {
        is UiState.Loading -> {
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.5f)),
//                contentAlignment = Alignment.Center
//            ) {
////                CircularProgressIndicator()
//            }
        }

        is UiState.Error -> {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                Text(
                    text = "Error: ${(loginState as UiState.Error).message}",
                    color = MaterialTheme.colorScheme.error
                )
            }
        }

        else -> {}
    }
}