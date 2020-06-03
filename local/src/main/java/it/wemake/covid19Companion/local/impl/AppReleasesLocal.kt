package it.wemake.covid19Companion.local.impl

import it.wemake.covid19Companion.data.local.IAppReleasesLocal
import it.wemake.covid19Companion.data.models.appReleases.AppReleaseEntity
import it.wemake.covid19Companion.local.mappers.toEntity
import it.wemake.covid19Companion.local.mappers.toLocal
import it.wemake.covid19Companion.local.room.dao.AppReleasesDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AppReleasesLocal @Inject constructor(
    private val appReleasesDao: AppReleasesDao
): IAppReleasesLocal{

    override suspend fun getAppReleases(): Flow<List<AppReleaseEntity>> =
        appReleasesDao.getAllAppReleases().map { it.map { it.toEntity() } }

    override suspend fun insertAppRelease(appRelease: AppReleaseEntity) {
        appReleasesDao.insertAppRelease(appRelease.toLocal())
    }

}