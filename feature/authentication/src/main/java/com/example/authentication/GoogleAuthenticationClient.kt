package com.example.authentication

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class GoogleAuthenticationClient {
    companion object {
        private const val TAG = "GoogleAuthClient"
        
        fun signInWithGoogle(
            activity: Activity,
            context: Context,
            scope: CoroutineScope,
            clientId: String,
            onResult: (Result<String>) -> Unit
        ) {
            val credentialManager = CredentialManager.create(context)
            
            val googleIdOption = GetGoogleIdOption.Builder()
                .setFilterByAuthorizedAccounts(false)
                .setServerClientId(clientId)
                .setNonce("test-nonce") // In production, use a secure random nonce
                .build()
            
            val request = GetCredentialRequest.Builder()
                .addCredentialOption(googleIdOption)
                .build()
            
            scope.launch {
                try {
                    val result = credentialManager.getCredential(activity, request)
                    handleSignInResult(result, onResult)
                } catch (e: Exception) {
                    Log.e(TAG, "Google Sign-In failed", e)
                    onResult(Result.failure(e))
                }
            }
        }
        
        private fun handleSignInResult(
            result: GetCredentialResponse,
            onResult: (Result<String>) -> Unit
        ) {
            when (val credential = result.credential) {
                is CustomCredential -> {
                    if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                        try {
                            val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                            val idToken = googleIdTokenCredential.idToken
                            onResult(Result.success(idToken))
                        } catch (e: GoogleIdTokenParsingException) {
                            Log.e(TAG, "Failed to parse Google ID token", e)
                            onResult(Result.failure(e))
                        }
                    } else {
                        Log.e(TAG, "Unexpected credential type: ${credential.type}")
                        onResult(Result.failure(IllegalArgumentException("Unexpected credential type: ${credential.type}")))
                    }
                }
                else -> {
                    Log.e(TAG, "Unexpected credential type: ${credential::class.simpleName}")
                    onResult(Result.failure(IllegalArgumentException("Unexpected credential type: ${credential::class.simpleName}")))
                }
            }
        }
    }
}