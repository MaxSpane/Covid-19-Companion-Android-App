package it.weMake.covid19Companion.ui.landing.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.k0d4black.theforce.commons.Loading
import com.k0d4black.theforce.commons.Success
import com.k0d4black.theforce.commons.UiStateViewModel
import it.weMake.covid19Companion.mappers.toPresentation
import it.weMake.covid19Companion.models.AreaCasesData
import it.weMake.covid19Companion.models.CountryCasesData
import it.weMake.covid19Companion.models.GlobalStats
import it.weMake.covid19Companion.models.PagedData
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
        val getGlobalCasesDataUseCase: GetGlobalCasesDataUseCase
//        val getCountriesUseCase: GetCountriesUseCase
    ) : UiStateViewModel() {

    val pagedCountriesCasesData: LiveData<PagedData<List<CountryCasesData>>>
        get() = _pagedCountriesCasesData

    private var _countriesCasesData: MutableLiveData<List<CountryCasesData>> =
        MutableLiveData()

    private var _pagedCountriesCasesData: MutableLiveData<PagedData<List<CountryCasesData>>> =
        MutableLiveData()

    val casesDataLastUpdated: LiveData<Long>
        get() = _casesDataLastUpdated

    private var _casesDataLastUpdated: MutableLiveData<Long> =
        MutableLiveData()

    val globalCasesData: LiveData<GlobalStats>
        get() = _globalCasesData

    private var _globalCasesData: MutableLiveData<GlobalStats> =
        MutableLiveData()

    private var page: Int = -1

    init {

//        loadNextPage()

        viewModelScope.launch {
            getCasesDataLastUpdatedUseCase().collect{ it ->
                _casesDataLastUpdated.value = it
            }
        }

        viewModelScope.launch {
            getGlobalCasesDataUseCase().collect{
                _globalCasesData.value = it.toPresentation()
            }
        }

//        viewModelScope.launch {
//            getCountriesUseCase().collect{
//                it.size
//            }
//        }

    }

//    fun loadNextPage(){
//        viewModelScope.launch(handler) {
//            getCountriesCasesDataUseCase(++page).collect{ countries ->
//
//                _pagedCountriesCasesData.value = PagedData(
//                    page,
//                    countries.map {
//                        it.toPresentation()
//                    }
//                )
//            }
//        }
//
//    }

    fun loadPage(page: Int, pageSize: Int = 10){
        viewModelScope.launch(handler) {
            getCountriesCasesDataUseCase(page, pageSize).collect{ countries ->

                _pagedCountriesCasesData.value = PagedData(
                    page,
                    countries.map {
                        it.toPresentation()
                    }
                )
            }
        }

    }

    fun updateCasesData(){
        _uiState.value = Loading
        viewModelScope.launch(handler) {
            updateCasesDataUseCase()
        }
        _uiState.value = Success
    }

    fun search(searchQuery: String){

//        if (searchQuery.isEmpty()){
//            _pagedCountriesCasesData.value = _countriesCasesData.value
//        }else{
//            viewModelScope.launch {
//                _pagedCountriesCasesData.value = _countriesCasesData.value!!.filter { countriesCasesData ->
//                    countriesCasesData.displayName.toLowerCase().contains(searchQuery.toLowerCase())
//                }
//            }
//        }

    }

}