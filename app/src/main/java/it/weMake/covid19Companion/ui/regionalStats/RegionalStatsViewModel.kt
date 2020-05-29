package it.weMake.covid19Companion.ui.regionalStats

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import it.weMake.covid19Companion.commons.Loading
import it.weMake.covid19Companion.commons.Success
import it.weMake.covid19Companion.commons.UiStateViewModel
import it.weMake.covid19Companion.mappers.toPresentation
import it.weMake.covid19Companion.models.casesData.RegionCasesData
import it.weMake.covid19Companion.utils.SORT_BY_CONFIRMED
import it.wemake.covid19Companion.domain.usecases.GetAllCountryRegionsCasesDataUseCase
import it.wemake.covid19Companion.domain.usecases.UpdateCountryRegionsCasesDataUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegionalStatsViewModel @Inject constructor(
    private val getAllCountryRegionsCasesDataUseCase: GetAllCountryRegionsCasesDataUseCase,
    private val updateCountryRegionsCasesDataUseCase: UpdateCountryRegionsCasesDataUseCase
) : UiStateViewModel(){

    private val _countryRegionsCasesDataLiveData = MutableLiveData<List<RegionCasesData>>()
    val countryRegionsCasesDataLiveData: LiveData<List<RegionCasesData>>
        get() = _countryRegionsCasesDataLiveData

    fun getAllCountryRegionsCasesData(countryName: String){
        viewModelScope.launch {
            getAllCountryRegionsCasesDataUseCase(countryName, SORT_BY_CONFIRMED).map { it.map { it.toPresentation() }}.collect {
                _countryRegionsCasesDataLiveData.value = it
            }
        }
    }

    fun updateCountryRegionsCasesData(countryName: String){
        _uiState.value = Loading
        viewModelScope.launch(handler) {
            updateCountryRegionsCasesDataUseCase(countryName)
            _uiState.value = Success
        }
    }

}