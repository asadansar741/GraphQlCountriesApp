package com.plcoding.graphqlcountriesapp.domain

class GetCountriesUseCases(
    private val countryClient: CountryClient
) {
    suspend fun execute():List<SimpleCountry>{
        return countryClient.getCountries().sortedBy { it.name }
    }
}