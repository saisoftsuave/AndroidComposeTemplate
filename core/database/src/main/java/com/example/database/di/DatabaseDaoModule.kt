package com.example.database.di

import com.example.database.AppDatabase
import com.example.database.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseDaoModule {
    @Provides
    fun providesTopicsDao(
        database: AppDatabase,
    ): UserDao = database.userDao()
}