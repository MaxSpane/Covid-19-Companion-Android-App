package it.wemake.covid19Companion.data.local

import it.wemake.covid19Companion.data.models.washHandsReminderLocations.WashHandsReminderLocationEntity
import kotlinx.coroutines.flow.Flow

interface IWashHandsReminderLocationsLocal {

    suspend fun getAllWashHandsReminderLocations(): Flow<List<WashHandsReminderLocationEntity>>

    suspend fun insertWashHandsReminderLocation(washHandsReminderLocation: WashHandsReminderLocationEntity): Long

    suspend fun deleteWashHandsReminderLocation(washHandsReminderLocation: WashHandsReminderLocationEntity)

    suspend fun updateWashHandsReminderLocation(washHandsReminderLocation: WashHandsReminderLocationEntity)

    suspend fun getWashHandsReminderLocation(id: Int): Flow<WashHandsReminderLocationEntity>

}