package com.example.auth.di

import com.example.auth.usecases.ValidateEmail
import com.example.auth.usecases.ValidatePassword
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

}