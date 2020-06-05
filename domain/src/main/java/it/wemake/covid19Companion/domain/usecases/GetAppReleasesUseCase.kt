package it.wemake.covid19Companion.domain.usecases

import it.wemake.covid19Companion.domain.repository.IAppReleasesRepository
import javax.inject.Inject

class GetAppReleasesUseCase
@Inject constructor(
    private val appReleasesRepository: IAppReleasesRepository
) {

    suspend operator fun invoke() = appReleasesRepository.getAppReleases()

}