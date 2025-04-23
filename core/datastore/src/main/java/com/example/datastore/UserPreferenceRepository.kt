package com.example.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferencesRepository(
    private val dataStore: DataStore<Preferences>,
    context: Context
) : UserPreferences {
    override val accessToken: Flow<String>
        get() = dataStore.data
            .map { preferences ->
                preferences[PreferenceKeys.ACCESS_TOKEN] ?: ""
            }

    override val refreshToken: Flow<String>
        get() = dataStore.data
            .map { preferences ->
                preferences[PreferenceKeys.ACCESS_TOKEN] ?: ""
            }


    override suspend fun updateAccessToken(
        accessToken: String,
        preferenceKey: Preferences.Key<String>
    ) {
        dataStore.edit { preferences ->
            preferences[preferenceKey] = accessToken
        }
    }

    override suspend fun updateRefreshToken(
        accessToken: String,
        preferenceKey: Preferences.Key<String>
    ) {
        dataStore.edit { preferences ->
            preferences[preferenceKey] = accessToken
        }
    }
}