package it.weMake.covid19Companion.ui.landing.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.weMake.covid19Companion.mappers.toPresentation
import it.weMake.covid19Companion.models.AreaCasesData
import it.wemake.covid19Companion.domain.usecases.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class DashboardViewModel
    @Inject constructor(
        val getAreaCasesDataUseCase: GetAreaCasesDataUseCase,
        val updateCasesDataUseCase: UpdateCasesDataUseCase,
        val getCasesDataLastUpdatedUseCase: GetCasesDataLastUpdatedUseCase,
        val getGlobalCasesDataUseCase: GetGlobalCasesDataUseCase
    ) : ViewModel() {

    val filteredAreaCasesData: LiveData<List<AreaCasesData>>
        get() = _filteredAreaCasesData

    private var _areaCasesData: MutableLiveData<List<AreaCasesData>> =
        MutableLiveData()

    private var _filteredAreaCasesData: MutableLiveData<List<AreaCasesData>> =
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
            getAreaCasesDataUseCase("world").collect{ countries ->
                _areaCasesData.value = countries.map {
                    it.toPresentation()
                }
                _filteredAreaCasesData.value = _areaCasesData.value
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

    }

    fun updateCasesSummary(){
        viewModelScope.launch(handler) {
            updateCasesDataUseCase()
        }
    }

    fun search(searchQuery: String){

        if (searchQuery.isEmpty()){
            _filteredAreaCasesData.value = _areaCasesData.value
        }else{
            viewModelScope.launch {
                _filteredAreaCasesData.value = _areaCasesData.value!!.filter { areaCasesData ->
                    areaCasesData.displayName.toLowerCase().contains(searchQuery.toLowerCase())
                }
            }
        }

    }

}