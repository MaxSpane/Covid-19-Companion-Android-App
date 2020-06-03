package it.wemake.covid19Companion.domain.repository

import it.wemake.covid19Companion.domain.models.appReleases.AppReleaseDomainModel
import kotlinx.coroutines.flow.Flow

interface IAppReleasesRepository {

    suspend fun getAppReleases(): Flow<List<AppReleaseDomainModel>>

    suspend fun insertAppRelease(appRelease: AppReleaseDomainModel)

}