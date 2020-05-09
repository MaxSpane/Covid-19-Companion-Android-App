package it.wemake.covid19Companion.domain.usecases

import it.wemake.covid19Companion.domain.repository.ISharedPreferencesRepository
import javax.inject.Inject

class SetUserCountryIso2UseCase @Inject constructor(
    private val sharedPreferencesRepository: ISharedPreferencesRepository
) {

    operator fun invoke(userCountryIso2: String) = sharedPreferencesRepository.setUserCountryIso2(userCountryIso2)

}