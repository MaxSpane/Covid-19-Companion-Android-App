package it.wemake.covid19Companion.domain.usecases

import it.wemake.covid19Companion.domain.repository.ICovid19CasesRepository
import it.wemake.covid19Companion.domain.repository.IWashHandsReminderLocationsRepository
import javax.inject.Inject

class GetAllWashHandsReminderLocationsUseCase @Inject constructor(
    private val washHandsReminderLocationsRepository: IWashHandsReminderLocationsRepository
) {

    suspend operator fun invoke() = washHandsReminderLocationsRepository.getAllWashHandsReminderLocations()

}