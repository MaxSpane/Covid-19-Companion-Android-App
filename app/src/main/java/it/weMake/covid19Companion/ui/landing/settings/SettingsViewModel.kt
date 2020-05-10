package it.weMake.covid19Companion.ui.landing.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import it.wemake.covid19Companion.domain.usecases.GetWashHandsIntervalUseCase
import it.wemake.covid19Companion.domain.usecases.SetWashHandsIntervalUseCase
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val getWashHandsIntervalUseCase: GetWashHandsIntervalUseCase,
    private val setWashHandsIntervalUseCase: SetWashHandsIntervalUseCase
) : ViewModel() {

    fun getWashHandsInterval(): Int =
        getWashHandsIntervalUseCase()

    fun setWashHandsInterval(interval: Int) =
        setWashHandsIntervalUseCase(interval)

}