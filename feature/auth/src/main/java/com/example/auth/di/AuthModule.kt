package com.example.auth.di

import androidx.datastore.core.DataStore
import com.example.auth.presentation.login.LoginViewModel
import com.example.auth.usecases.ValidateEmail
import com.example.auth.usecases.ValidatePassword
import com.example.datastore.UserPreferences
import com.example.network.auth.repository.AuthRepository
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
        dataStore: UserPreferences
    ) = LoginViewModel(
        validateEmail,
        validatePassword,
        authRepository,
        dataStore
    )

}