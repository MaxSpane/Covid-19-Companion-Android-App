package it.wemake.covid19Companion.data.local

import it.wemake.covid19Companion.data.models.appReleases.AppReleaseEntity
import kotlinx.coroutines.flow.Flow

interface IAppReleasesLocal {

    suspend fun getAppReleases(): Flow<List<AppReleaseEntity>>

    suspend fun insertAppRelease(appRelease: AppReleaseEntity)

}