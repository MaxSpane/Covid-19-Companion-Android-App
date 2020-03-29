package it.weMake.covid19Companion.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.weMake.covid19Companion.mappers.toPresentation
import it.weMake.covid19Companion.models.StarWarsCharacterUiModel
import it.wemake.covid19Companion.domain.usecases.SearchStarWarsCharacterUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class DashboardViewModel
    @Inject constructor(
        val characterUseCase: SearchStarWarsCharacterUseCase
    ) : ViewModel() {

    val searchResultsStarWars: LiveData<List<StarWarsCharacterUiModel>>
        get() = _searchResultsStarWars

    private var _searchResultsStarWars: MutableLiveData<List<StarWarsCharacterUiModel>> =
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

}