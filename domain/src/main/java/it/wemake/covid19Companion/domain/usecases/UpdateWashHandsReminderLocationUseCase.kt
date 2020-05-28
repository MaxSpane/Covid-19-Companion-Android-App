package it.wemake.covid19Companion.domain.usecases

import it.wemake.covid19Companion.domain.models.washHandsReminderLocations.WashHandsReminderLocationDomainModel
import it.wemake.covid19Companion.domain.repository.ICovid19CasesRepository
import it.wemake.covid19Companion.domain.repository.IWashHandsReminderLocationsRepository
import javax.inject.Inject

class UpdateWashHandsReminderLocationUseCase @Inject constructor(
    private val washHandsReminderLocationsRepository: IWashHandsReminderLocationsRepository
) {

    suspend operator fun invoke(washHandsReminderLocation: WashHandsReminderLocationDomainModel) =
        washHandsReminderLocationsRepository.updateWashHandsReminderLocation(washHandsReminderLocation)

}