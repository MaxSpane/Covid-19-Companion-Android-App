package it.weMake.covid19Companion.ui.splashscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.wemake.covid19Companion.domain.usecases.GetIsFirstLaunchUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashScreenViewModel @Inject constructor(
    private val getIsFirstLaunchUseCase: GetIsFirstLaunchUseCase
) : ViewModel(){

    private val _getIsFirstLaunch = MutableLiveData<Boolean>()
    val isFirstLaunch: LiveData<Boolean>
        get() = _getIsFirstLaunch

    init{
        viewModelScope.launch {
            _getIsFirstLaunch.value = getIsFirstLaunchUseCase()
        }
    }

}