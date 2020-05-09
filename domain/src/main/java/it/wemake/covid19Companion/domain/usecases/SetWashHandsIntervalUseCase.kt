package it.wemake.covid19Companion.domain.usecases

import it.wemake.covid19Companion.domain.repository.ISharedPreferencesRepository
import javax.inject.Inject

class SetWashHandsIntervalUseCase @Inject constructor(
    private val sharedPreferencesRepository: ISharedPreferencesRepository
) {

    operator fun invoke(interval: Int) = sharedPreferencesRepository.setWashHandsInterval(interval)

}