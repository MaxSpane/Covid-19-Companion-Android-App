package it.wemake.covid19Companion.domain.usecases

import it.wemake.covid19Companion.domain.repository.ISharedPreferencesRepository
import javax.inject.Inject

class SetRemindUserToWashHandsWhenArrivedAtLocationUseCase @Inject constructor(
    private val sharedPreferencesRepository: ISharedPreferencesRepository
) {

    operator fun invoke(remindUserToWashHandsWhenArrivedAtLocation: Boolean) =
        sharedPreferencesRepository.setRemindUserToWashHandsWhenArrivedAtLocation(remindUserToWashHandsWhenArrivedAtLocation)

}