package it.wemake.covid19Companion.domain.usecases

import it.wemake.covid19Companion.domain.repository.IWashHandsReminderLocationsRepository
import javax.inject.Inject

class GetWashHandsReminderLocationUseCase @Inject constructor(
    private val washHandsReminderLocationsRepository: IWashHandsReminderLocationsRepository
) {

    suspend operator fun invoke(id: Int) = washHandsReminderLocationsRepository.getWashHandsReminderLocation(id)

}