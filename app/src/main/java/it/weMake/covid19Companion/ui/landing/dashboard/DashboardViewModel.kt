package it.weMake.covid19Companion.ui.landing.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.weMake.covid19Companion.mappers.toDomain
import it.weMake.covid19Companion.mappers.toPresentation
import it.weMake.covid19Companion.models.Country
import it.weMake.covid19Companion.models.StarWarsCharacterUiModel
import it.wemake.covid19Companion.domain.usecases.GetCountriesUseCase
import it.wemake.covid19Companion.domain.usecases.GetNumberOfTriesUseCase
import it.wemake.covid19Companion.domain.usecases.InsertCountriesUseCase
import it.wemake.covid19Companion.domain.usecases.SearchStarWarsCharacterUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class DashboardViewModel
    @Inject constructor(
        val characterUseCase: SearchStarWarsCharacterUseCase,
        val getCountriesUseCase: GetCountriesUseCase,
        val insertCountriesUseCase: InsertCountriesUseCase,
        val getNumberOfTriesUseCase: GetNumberOfTriesUseCase
    ) : ViewModel() {

    val searchResultsStarWars: LiveData<List<StarWarsCharacterUiModel>>
        get() = _searchResultsStarWars

    private var _searchResultsStarWars: MutableLiveData<List<StarWarsCharacterUiModel>> =
        MutableLiveData()

    val fromRoomDB: LiveData<List<Country>>
        get() = _fromRoomDB

    private var _fromRoomDB: MutableLiveData<List<Country>> =
        MutableLiveData()

    val numberOfTries: LiveData<Int>
        get() = _numberOfTries

    private var _numberOfTries: MutableLiveData<Int> =
        MutableLiveData()

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    fun search(params: String){
        viewModelScope.launch(handler) {
            characterUseCase(params).collect { results ->
                _searchResultsStarWars.value = results.map {
                    it.toPresentation()
                }
            }
        }
    }

    protected val handler = CoroutineExceptionHandler { _, exception ->
//        _uiState.value = Error(exception)
    }

    init {

        viewModelScope.launch(handler) {
            getCountriesUseCase().collect{countries ->
                _fromRoomDB.value = countries.map {
                    it.toPresentation()
                }
            }
        }

    }

    fun insert(){
        val countries = listOf<Country>(Country("nigeria", "Nigeria"), Country("america", "America"))

        viewModelScope.launch(handler) {
            insertCountriesUseCase(countries.map {
                it.toDomain()
            })
        }

    }

    fun getNumberOfTries(){
        viewModelScope.launch(handler) {
            getNumberOfTriesUseCase().collect{
                _numberOfTries.value = it
            }
        }

    }

}