package it.weMake.covid19Companion.ui.landing.help

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.weMake.covid19Companion.mappers.toPresentation
import it.weMake.covid19Companion.models.PreventionTip
import it.wemake.covid19Companion.domain.usecases.GetPreventionTipsUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class HelpViewModel
    @Inject constructor(
        private val getPreventionTipsUseCase: GetPreventionTipsUseCase
    ): ViewModel(){

    private val _preventionTipsLiveData = MutableLiveData<List<PreventionTip>>()
    val preventionTipsLiveData: LiveData<List<PreventionTip>>
        get() = _preventionTipsLiveData

    init {

        viewModelScope.launch {
            getPreventionTipsUseCase().collect { preventionTips ->
                _preventionTipsLiveData.value = preventionTips.map { it.toPresentation() }
            }
        }

    }

}