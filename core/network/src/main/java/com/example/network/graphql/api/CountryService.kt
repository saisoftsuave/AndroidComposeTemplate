package com.example.network.graphql.api

import com.example.network.BaseResponse
import com.example.network.CountriesQuery
import com.example.network.CountryQuery
import com.example.network.auth.model.LoginResponse
import com.example.network.auth.model.LoginRequest
import com.example.network.auth.model.TestResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CountryService {
    suspend fun getCountries(): List<CountriesQuery.Country>?

    suspend fun getCountry(code: String): CountryQuery.Country?
}