package it.weMake.covid19Companion.ui.splashscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.weMake.covid19Companion.mappers.toPresentation
import it.weMake.covid19Companion.models.Country
import it.wemake.covid19Companion.domain.usecases.GetCountriesUseCase
import it.wemake.covid19Companion.domain.usecases.GetUserCountryIso2UseCase
import it.wemake.covid19Companion.domain.usecases.SetUserCountryIso2UseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashScreenViewModel @Inject constructor(
    private val getUserCountryIso2UseCase: GetUserCountryIso2UseCase,
    private val getCountriesUseCase: GetCountriesUseCase,
    private val setUserCountryIso2UseCase: SetUserCountryIso2UseCase
) : ViewModel(){

    lateinit var userCountryIso2: String
    lateinit var countries: List<Country>
    private val _countriesLiveData = MutableLiveData<List<Country>>()
    val countriesLiveData: LiveData<List<Country>> = _countriesLiveData
    private val _userCountryIso2LiveData = MutableLiveData<String>()
    val userCountryIso2LiveData: LiveData<String> = _userCountryIso2LiveData

    init{
        viewModelScope.launch {
            _userCountryIso2LiveData.value = getUserCountryIso2UseCase()
        }

        viewModelScope.launch {
            getCountriesUseCase().map { it.map { country-> country.toPresentation() } }.collect {
                _countriesLiveData.value = it
            }
        }
    }

    fun setUserCountryIso(userCountryIso2: String){
        viewModelScope.launch {
            setUserCountryIso2UseCase(userCountryIso2)
        }
    }

}