package com.plcoding.graphqlcountriesapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.graphqlcountriesapp.domain.DetailedCountry
import com.plcoding.graphqlcountriesapp.domain.GetCountriesUseCases
import com.plcoding.graphqlcountriesapp.domain.GetCountryUseCases
import com.plcoding.graphqlcountriesapp.domain.SimpleCountry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val getCountriesUseCases: GetCountriesUseCases,
    private val getCountryUseCases: GetCountryUseCases
): ViewModel() {

    private val _state = MutableStateFlow(CountriesState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update { it.copy(
                isLoading = true,
            ) }
            _state.update { it.copy(
                countries = getCountriesUseCases.execute(),
                isLoading = false
            ) }
        }
    }

    fun selectCountry(code:String){
        viewModelScope.launch {
            _state.update { it.copy(
                selectedCountry = getCountryUseCases.execute(code)
            ) }
        }
    }

    fun dismissCountryDialog(){
        _state.update { it.copy(
            selectedCountry = null
        ) }
    }

    data class CountriesState(
        val countries: List<SimpleCountry> = emptyList(),
        val isLoading: Boolean = false,
        val selectedCountry: DetailedCountry? = null
    )
}