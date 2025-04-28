package com.example.network.auth.di


import android.content.Context
import com.example.network.RetrofitClient
import com.example.network.auth.repository.AuthRepository
import com.example.network.auth.repository.AuthRepositoryImpl
import com.example.network.auth.api.AuthService
import com.example.network.product.ProductRepository
import com.example.network.product.ProductRepositoryImpl
import com.example.network.product.api.ProductService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(@ApplicationContext context: Context): Retrofit {
        return RetrofitClient.createRetrofit(context, isDebug = true, useMock = true)
    }

    @Provides
    @Singleton
    fun provideProductService(retrofit: Retrofit): ProductService {
        return retrofit.create(ProductService::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthService(retrofit: Retrofit): AuthService {
        return retrofit.create(AuthService::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(authService: AuthService): AuthRepository {
        return AuthRepositoryImpl(authService)
    }
    @Provides
    @Singleton
    fun provideProductRepository(productService: ProductService): ProductRepository {
        return ProductRepositoryImpl(productService)
    }
}