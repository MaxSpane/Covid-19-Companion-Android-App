package it.wemake.covid19Companion.domain.repository

import it.wemake.covid19Companion.domain.models.washHandsReminderLocations.WashHandsReminderLocationDomainModel
import kotlinx.coroutines.flow.Flow

interface IWashHandsReminderLocationsRepository {

    suspend fun getAllWashHandsReminderLocations(): Flow<List<WashHandsReminderLocationDomainModel>>

    suspend fun insertWashHandsReminderLocation(washHandsReminderLocation: WashHandsReminderLocationDomainModel): Long

    suspend fun deleteWashHandsReminderLocation(washHandsReminderLocation: WashHandsReminderLocationDomainModel)

    suspend fun updateWashHandsReminderLocation(washHandsReminderLocation: WashHandsReminderLocationDomainModel)

    suspend fun getWashHandsReminderLocation(id: Int): Flow<WashHandsReminderLocationDomainModel>

}