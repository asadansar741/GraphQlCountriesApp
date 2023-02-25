package com.plcoding.graphqlcountriesapp.domain

class GetCountryUseCases(
    private val countryClient: CountryClient
) {
    suspend fun execute(code:String):DetailedCountry?{
        return countryClient.getCountry(code)
    }
}