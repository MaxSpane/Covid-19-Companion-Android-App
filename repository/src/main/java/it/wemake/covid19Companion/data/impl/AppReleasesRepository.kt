package it.wemake.covid19Companion.data.impl

import it.wemake.covid19Companion.data.local.IAppReleasesLocal
import it.wemake.covid19Companion.data.mappers.toDomain
import it.wemake.covid19Companion.data.mappers.toEntity
import it.wemake.covid19Companion.domain.models.appReleases.AppReleaseDomainModel
import it.wemake.covid19Companion.domain.repository.IAppReleasesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AppReleasesRepository
    @Inject constructor(
        private val appReleasesLocal: IAppReleasesLocal
    ): IAppReleasesRepository {

    override suspend fun getAppReleases(): Flow<List<AppReleaseDomainModel>> =
        appReleasesLocal.getAppReleases().map { it.map { it.toDomain() } }

    override suspend fun insertAppRelease(appRelease: AppReleaseDomainModel) {
        appReleasesLocal.insertAppRelease(
            appRelease.toEntity()
        )
    }

}