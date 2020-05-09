package it.wemake.covid19Companion.domain.usecases

import it.wemake.covid19Companion.domain.repository.ICountriesRepository
import javax.inject.Inject

class GetCountriesUseCase
@Inject constructor(
    private val countriesRepository: ICountriesRepository
) {

    suspend operator fun invoke() = countriesRepository.getCountries()

}