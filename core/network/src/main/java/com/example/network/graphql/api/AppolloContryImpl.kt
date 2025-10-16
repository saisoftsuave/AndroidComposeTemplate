package com.example.network.graphql.api

import com.apollographql.apollo.ApolloClient
import com.example.network.CountriesQuery
import com.example.network.CountryQuery

class AppolloCountryImpl(
    private val apolloClient : ApolloClient
) : CountryService {
    override suspend fun getCountries(): List<CountriesQuery.Country>? {
        val response = apolloClient.query(CountriesQuery()).execute()
        return response.data?.countries
    }

    override suspend fun getCountry(code: String): CountryQuery.Country? {
        val response = apolloClient.query(CountryQuery()).execute()
        return response.data?.country
    }
}