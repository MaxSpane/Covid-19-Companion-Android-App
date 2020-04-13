package it.weMake.covid19Companion.ui.landing.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.weMake.covid19Companion.mappers.toPresentation
import it.weMake.covid19Companion.models.AreaCasesData
import it.weMake.covid19Companion.models.CountryCasesData
import it.wemake.covid19Companion.domain.usecases.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class DashboardViewModel
    @Inject constructor(
        val getCountriesCasesDataUseCase: GetCountriesCasesDataUseCase,
        val updateCasesDataUseCase: UpdateCasesDataUseCase,
        val getCasesDataLastUpdatedUseCase: GetCasesDataLastUpdatedUseCase,
        val getGlobalCasesDataUseCase: GetGlobalCasesDataUseCase,
        val getCountriesUseCase: GetCountriesUseCase
    ) : ViewModel() {

    val filteredCountriesCasesData: LiveData<List<CountryCasesData>>
        get() = _filteredCountriesCasesData

    private var _countriesCasesData: MutableLiveData<List<CountryCasesData>> =
        MutableLiveData()

    private var _filteredCountriesCasesData: MutableLiveData<List<CountryCasesData>> =
        MutableLiveData()

    val casesDataLastUpdated: LiveData<String>
        get() = _casesDataLastUpdated

    private var _casesDataLastUpdated: MutableLiveData<String> =
        MutableLiveData()

    val globalCasesData: LiveData<AreaCasesData>
        get() = _globalCasesData

    private var _globalCasesData: MutableLiveData<AreaCasesData> =
        MutableLiveData()

    protected val handler = CoroutineExceptionHandler { _, exception ->
        exception.printStackTrace()
//        _uiState.value = Error(exception)
    }

    init {

        viewModelScope.launch(handler) {
            getCountriesCasesDataUseCase().collect{ countries ->
                _countriesCasesData.value = countries.map {
                    it.toPresentation()
                }
                _filteredCountriesCasesData.value = _countriesCasesData.value
            }
        }

        viewModelScope.launch {
            getCasesDataLastUpdatedUseCase().collect{ it ->
                _casesDataLastUpdated.value = it
            }
        }

        viewModelScope.launch {
            getGlobalCasesDataUseCase().collect{
                _globalCasesData.value = it?.toPresentation()
            }
        }

        viewModelScope.launch {
            getCountriesUseCase().collect{
                it.size
            }
        }

    }

    fun updateCasesSummary(){
        viewModelScope.launch(handler) {
            updateCasesDataUseCase()
        }
    }

    fun search(searchQuery: String){

        if (searchQuery.isEmpty()){
            _filteredCountriesCasesData.value = _countriesCasesData.value
        }else{
            viewModelScope.launch {
                _filteredCountriesCasesData.value = _countriesCasesData.value!!.filter { countriesCasesData ->
                    countriesCasesData.displayName.toLowerCase().contains(searchQuery.toLowerCase())
                }
            }
        }

    }

}