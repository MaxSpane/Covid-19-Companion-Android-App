package it.wemake.covid19Companion.data.impl

import it.wemake.covid19Companion.data.local.IWashHandsReminderLocationsLocal
import it.wemake.covid19Companion.data.mappers.toDomain
import it.wemake.covid19Companion.data.mappers.toEntity
import it.wemake.covid19Companion.domain.models.washHandsReminderLocations.WashHandsReminderLocationDomainModel
import it.wemake.covid19Companion.domain.repository.IWashHandsReminderLocationsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WashHandsReminderLocationsRepository @Inject constructor(
    private val washHandsReminderLocationsLocal: IWashHandsReminderLocationsLocal
): IWashHandsReminderLocationsRepository{

    override suspend fun getAllWashHandsReminderLocations(): Flow<List<WashHandsReminderLocationDomainModel>> =
        washHandsReminderLocationsLocal.getAllWashHandsReminderLocations().map {washHandsReminderLocations->
            washHandsReminderLocations.map {
                it.toDomain()
            }
        }

    override suspend fun insertWashHandsReminderLocation(washHandsReminderLocation: WashHandsReminderLocationDomainModel): Long =
        washHandsReminderLocationsLocal.insertWashHandsReminderLocation(
            washHandsReminderLocation.toEntity()
        )

    override suspend fun deleteWashHandsReminderLocation(washHandsReminderLocation: WashHandsReminderLocationDomainModel) =
        washHandsReminderLocationsLocal.deleteWashHandsReminderLocation(
            washHandsReminderLocation.toEntity()
        )

    override suspend fun updateWashHandsReminderLocation(washHandsReminderLocation: WashHandsReminderLocationDomainModel) =
        washHandsReminderLocationsLocal.updateWashHandsReminderLocation(
            washHandsReminderLocation.toEntity()
        )

    override suspend fun getWashHandsReminderLocation(id: Int): Flow<WashHandsReminderLocationDomainModel> =
        washHandsReminderLocationsLocal.getWashHandsReminderLocation(id).map {
            it.toDomain()
        }

}