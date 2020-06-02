package it.weMake.covid19Companion.ui.landing.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.weMake.covid19Companion.mappers.toDomain
import it.weMake.covid19Companion.mappers.toPresentation
import it.weMake.covid19Companion.models.Country
import it.weMake.covid19Companion.models.washHandsReminderLocations.WashHandsReminderLocation
import it.wemake.covid19Companion.domain.usecases.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingsViewModel @Inject constructor(

    private val getWashHandsIntervalUseCase: GetWashHandsIntervalUseCase,
    private val setWashHandsIntervalUseCase: SetWashHandsIntervalUseCase,
    private val getDrinkWaterIntervalUseCase: GetDrinkWaterIntervalUseCase,
    private val setDrinkWaterIntervalUseCase: SetDrinkWaterIntervalUseCase,
    private val getUseCustomNotificationToneUseCase: GetUseCustomNotificationToneUseCase,
    private val setUseCustomNotificationToneUseCase: SetUseCustomNotificationToneUseCase,
    private val getAllWashHandsReminderLocationsUseCase: GetAllWashHandsReminderLocationsUseCase,
    private val insertWashHandsReminderLocationUseCase: InsertWashHandsReminderLocationUseCase,
    private val deleteWashHandsReminderLocationUseCase: DeleteWashHandsReminderLocationUseCase,
    private val updateWashHandsReminderLocationUseCase: UpdateWashHandsReminderLocationUseCase,
    private val setRemindUserToWashHandsWhenArrivedAtLocationUseCase: SetRemindUserToWashHandsWhenArrivedAtLocationUseCase,
    private val getRemindUserToWashHandsWhenArrivedAtLocationUseCase: GetRemindUserToWashHandsWhenArrivedAtLocationUseCase,
    private val getUsernameUseCase: GetUsernameUseCase,
    private val setUsernameUseCase: SetUsernameUseCase,
    private val getUserCountryIso2UseCase: GetUserCountryIso2UseCase,
    private val getCountriesUseCase: GetCountriesUseCase,
    private val setUserCountryIso2UseCase: SetUserCountryIso2UseCase

    ) : ViewModel() {
    private val _countriesLiveData = MutableLiveData<List<Country>>()
    val countriesLiveData: LiveData<List<Country>> = _countriesLiveData
    private val _userCountryIso2LiveData = MutableLiveData<String>()
    val userCountryIso2LiveData: LiveData<String> = _userCountryIso2LiveData
    private val _washHandsReminderLocationsLiveData = MutableLiveData<List<WashHandsReminderLocation>>()
    val washHandsReminderLocationsLiveData: LiveData<List<WashHandsReminderLocation>>
        get() = _washHandsReminderLocationsLiveData

    private val _insertedWashHandsReminderLocationsLiveData = MutableLiveData<WashHandsReminderLocation?>()
    val insertedWashHandsReminderLocationsLiveData: LiveData<WashHandsReminderLocation?>
        get() = _insertedWashHandsReminderLocationsLiveData

    init {

        viewModelScope.launch {
            getAllWashHandsReminderLocationsUseCase().map {washHandsLocations-> washHandsLocations.map { it.toPresentation() } }
                .collect {
                    _washHandsReminderLocationsLiveData.value = it
                }
        }
        viewModelScope.launch {
            _userCountryIso2LiveData.value = getUserCountryIso2UseCase()
        }
        viewModelScope.launch {
            getCountriesUseCase().map { it.map { country-> country.toPresentation() } }.collect {
                _countriesLiveData.value = it
            }
        }
    }

    fun setUserCountryIso(userCountryIso2: String){
        viewModelScope.launch {
            setUserCountryIso2UseCase(userCountryIso2)
        }
    }
    fun getWashHandsInterval(): Int =
        getWashHandsIntervalUseCase()

    fun setWashHandsInterval(interval: Int) =
        setWashHandsIntervalUseCase(interval)

    fun getDrinkWaterInterval(): Int =
        getDrinkWaterIntervalUseCase()

    fun setDrinkWaterInterval(interval: Int) =
        setDrinkWaterIntervalUseCase(interval)

    fun getUseCustomNotificationTone(): Boolean =
        getUseCustomNotificationToneUseCase()

    fun setUseCustomNotificationTone(useCustomNotificationTone: Boolean) =
        setUseCustomNotificationToneUseCase(useCustomNotificationTone)

    fun getUsername(): String =
        getUsernameUseCase()

    fun setUsername(username: String) =
        setUsernameUseCase(username)

    fun insertWashHandsReminderLocation(washHandsReminderLocation: WashHandsReminderLocation){
        viewModelScope.launch {
            washHandsReminderLocation.id = insertWashHandsReminderLocationUseCase(
                washHandsReminderLocation.toDomain()
            ).toInt()
            _insertedWashHandsReminderLocationsLiveData.value = washHandsReminderLocation
        }
    }

    fun resetInsertedWashHandsReminderLocationsLiveData(){
        _insertedWashHandsReminderLocationsLiveData.value = null
    }

    fun deleteWashHandsReminderLocation(washHandsReminderLocation: WashHandsReminderLocation){
        viewModelScope.launch {
            deleteWashHandsReminderLocationUseCase(
                washHandsReminderLocation.toDomain()
            )
        }
    }

    fun updateWashHandsReminderLocation(washHandsReminderLocation: WashHandsReminderLocation){
        viewModelScope.launch {
            updateWashHandsReminderLocationUseCase(
                washHandsReminderLocation.toDomain()
            )
        }
    }

    fun getRemindUserToWashHandsWhenArrivedAtLocation(): Boolean =
        getRemindUserToWashHandsWhenArrivedAtLocationUseCase()

    fun setRemindUserToWashHandsWhenArrivedAtLocation(remindUserToWashHandsWhenArrivedAtLocation: Boolean) =
        setRemindUserToWashHandsWhenArrivedAtLocationUseCase(remindUserToWashHandsWhenArrivedAtLocation)

}