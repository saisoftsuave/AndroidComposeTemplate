package com.example.network.graphql.model

import com.example.network.CountriesQuery
import com.example.network.CountryQuery

data class CountriesResponse(
    val countries: List<Country>
)


data class Country(
    val code: String,
    val name: String,
    val emoji: String
)


data class DetailedCountry(
    val code: String,
    val name: String,
    val native: String,
    val phone: String,
    val continent: String,
    val capital: String?,
    val currency: String?,
    val languages: List<String>,
    val emoji: String,
    val emojiU: String
)