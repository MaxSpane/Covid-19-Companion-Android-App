package it.weMake.covid19Companion.ui.landing.sortedDetailsData

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.weMake.covid19Companion.mappers.toPresentation
import it.weMake.covid19Companion.models.casesData.CountryCasesData
import it.weMake.covid19Companion.models.casesData.GlobalStats
import it.wemake.covid19Companion.domain.usecases.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class SortedDetailsDataViewModel @Inject constructor(
    private val getAllCountriesCasesDataUseCase: GetAllCountriesCasesDataUseCase,
    private val getGlobalCasesDataUseCase: GetGlobalCasesDataUseCase
) : ViewModel(){

    private val _countriesLiveData = MutableLiveData<List<CountryCasesData>>()
    val countriesLiveData: LiveData<List<CountryCasesData>> = _countriesLiveData
    val globalCasesData: LiveData<GlobalStats>
        get() = _globalCasesData

    private var _globalCasesData: MutableLiveData<GlobalStats> =
        MutableLiveData()

    init {

        viewModelScope.launch {
            getGlobalCasesDataUseCase().collect {
                _globalCasesData.value = it.toPresentation()
            }
        }

    }

    fun getCountryLiveData(sortValue : String) {
        viewModelScope.launch {
            getAllCountriesCasesDataUseCase(sortValue).map { it.map { country -> country.toPresentation() } }
                .collect {
                    _countriesLiveData.value = it
                }
        }
    }


}