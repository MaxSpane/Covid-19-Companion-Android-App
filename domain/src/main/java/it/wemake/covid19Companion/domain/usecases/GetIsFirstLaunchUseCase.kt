package it.wemake.covid19Companion.domain.usecases

import it.wemake.covid19Companion.domain.repository.ISharedPreferencesRepository
import javax.inject.Inject

class GetIsFirstLaunchUseCase @Inject constructor(
    private val sharedPreferencesRepository: ISharedPreferencesRepository
) {

    suspend operator fun invoke() = sharedPreferencesRepository.getIsFirstLaunch()

}