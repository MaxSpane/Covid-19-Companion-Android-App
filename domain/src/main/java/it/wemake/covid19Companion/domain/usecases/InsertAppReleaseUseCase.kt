package it.wemake.covid19Companion.domain.usecases

import it.wemake.covid19Companion.domain.models.appReleases.AppReleaseDomainModel
import it.wemake.covid19Companion.domain.repository.IAppReleasesRepository
import javax.inject.Inject

class InsertAppReleaseUseCase
@Inject constructor(
    private val appReleasesRepository: IAppReleasesRepository
) {

    suspend operator fun invoke(appRelease: AppReleaseDomainModel) = appReleasesRepository.insertAppRelease(appRelease)

}