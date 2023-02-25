package com.plcoding.graphqlcountriesapp.di

import com.apollographql.apollo3.ApolloClient
import com.plcoding.graphqlcountriesapp.data.ApolloCountryClient
import com.plcoding.graphqlcountriesapp.domain.CountryClient
import com.plcoding.graphqlcountriesapp.domain.GetCountriesUseCases
import com.plcoding.graphqlcountriesapp.domain.GetCountryUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesApolloClient() = ApolloClient.Builder()
        .serverUrl("https://countries.trevorblades.com/graphql")
        .build()

    @Provides
    @Singleton
    fun providesCountryClient(apolloClient: ApolloClient) : CountryClient =
        ApolloCountryClient(apolloClient)

    @Provides
    @Singleton
    fun providesGetCountriesUseCase(countryClient: CountryClient) : GetCountriesUseCases =
        GetCountriesUseCases(countryClient)

    @Provides
    @Singleton
    fun providesGetCountryUseCase(countryClient: CountryClient) : GetCountryUseCases =
        GetCountryUseCases(countryClient)
}