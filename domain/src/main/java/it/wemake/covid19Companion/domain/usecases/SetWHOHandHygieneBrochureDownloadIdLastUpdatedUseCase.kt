package it.wemake.covid19Companion.domain.usecases

import it.wemake.covid19Companion.domain.repository.ISharedPreferencesRepository
import javax.inject.Inject

class SetWHOHandHygieneBrochureDownloadIdLastUpdatedUseCase @Inject constructor(
    private val sharedPreferencesRepository: ISharedPreferencesRepository
) {

    operator fun invoke(downloadId: Long) = sharedPreferencesRepository.setWHOHandHygieneDownloadId(downloadId)

}