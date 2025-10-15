package com.example.authentication

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import androidx.credentials.PasswordCredential
import androidx.credentials.PublicKeyCredential
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class GoogleAuthenticationClint {

    companion object {
        fun getClient(
            context: Context,
            scope: CoroutineScope,
        ) {
            val credManager = CredentialManager.create(
                context = context
            )

            val request = GetCredentialRequest.Builder().addCredentialOption(
                getCredentialsOptions(context)
            ).build()

            try {
                val result =
                    scope.launch {
                        val result = credManager.getCredential(context, request)
                        handleSignIn(result)
                    }
            } catch (E: Exception) {
//                launcher.launch(
//                    Intent(
//                        Settings.ACTION_ADD_ACCOUNT
//                    ).apply {
//                        putExtra(Settings.EXTRA_ACCOUNT_TYPES, arrayOf("com.google")
//                    }
//                )
            }
        }

        fun getCredentialsOptions(
            context: Context
        ): GetGoogleIdOption {
            return GetGoogleIdOption.Builder().setFilterByAuthorizedAccounts(false)
                .setServerClientId(
                    context.getString(R.string.web_client_id)
                ).setNonce(
                    "test"
                )
                .build()
        }
    }


}


fun handleSignIn(result: GetCredentialResponse) {
    // Handle the successfully returned credential.
    val credential = result.credential
    val responseJson: String

    when (credential) {

        // Passkey credential
        is PublicKeyCredential -> {
            // Share responseJson such as a GetCredentialResponse to your server to validate and
            // authenticate
            responseJson = credential.authenticationResponseJson
        }

        // Password credential
        is PasswordCredential -> {
            // Send ID and password to your server to validate and authenticate.
            val username = credential.id
            val password = credential.password
        }

        // GoogleIdToken credential
        is CustomCredential -> {
            if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                try {
                    // Use googleIdTokenCredential and extract the ID to validate and
                    // authenticate on your server.
                    val googleIdTokenCredential = GoogleIdTokenCredential
                        .createFrom(credential.data)
                    // You can use the members of googleIdTokenCredential directly for UX
                    // purposes, but don't use them to store or control access to user
                    // data. For that you first need to validate the token:
                    // pass googleIdTokenCredential.getIdToken() to the backend server.
                    // see [validation instructions](https://developers.google.com/identity/gsi/web/guides/verify-google-id-token)
                } catch (e: GoogleIdTokenParsingException) {
                    Log.e(TAG, "Received an invalid google id token response", e)
                }
            } else {
                // Catch any unrecognized custom credential type here.
                Log.e(TAG, "Unexpected type of credential")
            }
        }

        else -> {
            // Catch any unrecognized credential type here.
            Log.e(TAG, "Unexpected type of credential")
        }
    }
}