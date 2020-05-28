package it.wemake.covid19Companion.local.impl

import it.wemake.covid19Companion.data.local.IWashHandsReminderLocationsLocal
import it.wemake.covid19Companion.data.models.washHandsReminderLocations.WashHandsReminderLocationEntity
import it.wemake.covid19Companion.local.mappers.toEntity
import it.wemake.covid19Companion.local.mappers.toLocal
import it.wemake.covid19Companion.local.room.dao.WashHandsReminderLocationsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WashHandsReminderLocationsLocal @Inject constructor(
    private val washHandsReminderLocationsDao: WashHandsReminderLocationsDao
): IWashHandsReminderLocationsLocal {

    override suspend fun getAllWashHandsReminderLocations(): Flow<List<WashHandsReminderLocationEntity>> =
        washHandsReminderLocationsDao.getAllWashHandsReminderLocations().map { washHandsReminderLocations->
            washHandsReminderLocations.map {
                it.toEntity()
            }
        }

    override suspend fun insertWashHandsReminderLocation(washHandsReminderLocation: WashHandsReminderLocationEntity): Long =
        withContext(Dispatchers.IO){
            washHandsReminderLocationsDao.insertWashHandsReminderLocation(
                washHandsReminderLocation.toLocal()
            )
        }

    override suspend fun deleteWashHandsReminderLocation(washHandsReminderLocation: WashHandsReminderLocationEntity) =
        withContext(Dispatchers.IO){
            washHandsReminderLocationsDao.deleteWashHandsReminderLocation(
                washHandsReminderLocation.toLocal()
            )
        }

    override suspend fun updateWashHandsReminderLocation(washHandsReminderLocation: WashHandsReminderLocationEntity) =
        withContext(Dispatchers.IO){
            washHandsReminderLocationsDao.updateWashHandsReminderLocation(
                washHandsReminderLocation.toLocal()
            )
        }

    override suspend fun getWashHandsReminderLocation(id: Int): Flow<WashHandsReminderLocationEntity> =
        washHandsReminderLocationsDao.getWashHandsReminderLocation(id).map {
            it.toEntity()
        }

}