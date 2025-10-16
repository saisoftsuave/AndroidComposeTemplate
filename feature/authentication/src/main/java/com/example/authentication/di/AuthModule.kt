package com.example.authentication.di

import androidx.datastore.core.DataStore
import com.example.authentication.presentation.login.LoginViewModel
import com.example.authentication.usecases.ValidateEmail
import com.example.authentication.usecases.ValidatePassword
import com.example.datastore.UserPreferences
import com.example.network.auth.repository.AuthRepository
import com.example.network.graphql.api.CountryService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AuthModule {

    @Provides
    @Singleton
    fun provideValidateEmail() = ValidateEmail()

    @Provides
    @Singleton
    fun provideValidatePassword() = ValidatePassword()


    @Provides
    @Singleton
    fun provideLoginViewModel(
        validateEmail: ValidateEmail,
        validatePassword: ValidatePassword,
        authRepository: AuthRepository,
        dataStore: UserPreferences,
        contryService : CountryService
    ) = LoginViewModel(
        validateEmail,
        validatePassword,
        authRepository,
        dataStore,
        contryService
    )

}