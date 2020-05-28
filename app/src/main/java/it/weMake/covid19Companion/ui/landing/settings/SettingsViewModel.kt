package it.weMake.covid19Companion.ui.landing.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.weMake.covid19Companion.mappers.toDomain
import it.weMake.covid19Companion.mappers.toPresentation
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
    private val getRemindUserToWashHandsWhenArrivedAtLocationUseCase: GetRemindUserToWashHandsWhenArrivedAtLocationUseCase
) : ViewModel() {

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