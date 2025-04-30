package com.example.datastore

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface UserPreferences {
    val accessToken: Flow<String>
    val refreshToken: Flow<String>

    suspend fun updateAccessToken(
        accessToken: String,
        preferenceKey: Preferences.Key<String> = PreferenceKeys.ACCESS_TOKEN
    )

    suspend fun updateRefreshToken(
        accessToken: String,
        preferenceKey: Preferences.Key<String> = PreferenceKeys.REFRESH_TOKEN
    )
}