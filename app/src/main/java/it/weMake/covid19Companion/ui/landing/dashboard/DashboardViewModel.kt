package it.weMake.covid19Companion.ui.landing.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.weMake.covid19Companion.mappers.toPresentation
import it.weMake.covid19Companion.models.CasesStats
import it.weMake.covid19Companion.models.CountryCases
import it.wemake.covid19Companion.domain.usecases.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class DashboardViewModel
    @Inject constructor(
        val getCountriesCasesUseCase: GetCountriesCasesUseCase,
        val updateCountriesCasesUseCase: UpdateCountriesCasesUseCase,
        val getCountriesCasesSummaryLastUpdatedUseCase: GetCountriesCasesSummaryLastUpdatedUseCase,
        val getCasesStatsUseCase: CasesStatsUseCase
    ) : ViewModel() {

    val countryCases: LiveData<List<CountryCases>>
        get() = _countryCases

    private var _countryCases: MutableLiveData<List<CountryCases>> =
        MutableLiveData()

    val countryCasesLastUpdated: LiveData<String>
        get() = _countryCasesLastUpdated

    private var _countryCasesLastUpdated: MutableLiveData<String> =
        MutableLiveData()

    val casesStats: LiveData<CasesStats>
        get() = _casesStats

    private var _casesStats: MutableLiveData<CasesStats> =
        MutableLiveData()

    protected val handler = CoroutineExceptionHandler { _, exception ->
        exception.printStackTrace()
//        _uiState.value = Error(exception)
    }

    init {

        viewModelScope.launch(handler) {
            getCountriesCasesUseCase().collect{ countries ->
                _countryCases.value = countries.map {
                    it.toPresentation()
                }
            }
        }

        viewModelScope.launch {
            getCountriesCasesSummaryLastUpdatedUseCase().collect{ it ->
                _countryCasesLastUpdated.value = it
            }
        }

        viewModelScope.launch {
            getCasesStatsUseCase().collect{
                _casesStats.value = it.toPresentation()
            }
        }

    }

    fun updateCasesSummary(){
        viewModelScope.launch(handler) {
            updateCountriesCasesUseCase()
        }
    }


}