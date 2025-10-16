package com.example.network.graphql.di


import android.content.Context
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.network.okHttpClient
import com.example.network.RetrofitClient
import com.example.network.auth.repository.AuthRepository
import com.example.network.auth.repository.AuthRepositoryImpl
import com.example.network.auth.api.AuthService
import com.example.network.graphql.api.AppolloCountryImpl
import com.example.network.graphql.api.CountryService
import com.example.network.services.ServicesRepository
import com.example.network.services.ServicesRepositoryImpl
import com.example.network.services.api.ServicesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CountryModule {

    @Provides
    @Singleton
    fun provideApolloClient(): ApolloClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

        return ApolloClient.Builder()
            .serverUrl("https://countries.trevorblades.com/")
            .okHttpClient(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideAppolloClient(
        apolloClient: ApolloClient
    ): CountryService {
        return AppolloCountryImpl(
            apolloClient = apolloClient
        )
    }
}